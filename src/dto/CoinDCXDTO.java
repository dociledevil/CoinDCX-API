package dto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Charsets;

import currency.CoindcxCurrencyPair;
import dto.marketdata.LastTrade;
import dto.marketdata.Order;
import dto.marketdata.Order.OrderType;
import dto.marketdata.Order.Side;
import dto.marketdata.Ticker;
import dto.orderbook.Asks;
import dto.orderbook.Bids;
import dto.orderbook.Orderbook;
import dto.userdata.Balance;
import utils.CoindcxApiException;
import utils.CoindcxRequestGenerator;

/**
 * @author dociledevil
 *	This class serves as the conduit between CoinDCX API and the information request from the user.
 */
public class CoinDCXDTO
{
	/**
	 * This mapper provides functionality for converting between Java objects and matching JSON constructs.
	 */
	final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Client is the main entry point to the fluent API used to build and execute client requests in order to consume responses returned.
	 */
	final Client client = ClientBuilder.newClient();

	/**
	 * A resource target identified by the resource URI. Depending upon the endpoint, the target will have paths/queryparams appended to it
	 */
	WebTarget target = client.target("https://api.coindcx.com/exchange");
	Response response;
	public CoinDCXDTO() {}
	/**
	 * Returns a list of tickers(one for each pair) as a list of Ticker class.
	 * @return List<Ticker>
	 * @throws IOException
	 */
	List<Ticker> getTicker() throws IOException { 
		response = target.path("ticker").request().get();
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode nodes = mapper.readTree(response.readEntity(String.class));
			List<Ticker> tickerList=new ArrayList<>();
			for (JsonNode node : nodes)
			{
				tickerList.add(mapper.readValue(node.toString(), Ticker.class));
			}
			return tickerList;
		}
		else
			throw new CoindcxApiException(response.getStatus());
	}

	/**
	 * Returns a list of properly sorted last 50 trades as a list of LastTrade class.
	 * @param market Currency Pair
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	List<LastTrade> getLastTrade(CoindcxCurrencyPair market) throws JsonParseException, JsonMappingException, IOException {
		response = target.path("v1").path("trades").path(market.toString().replace("_", "")).request().get();
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode nodes = mapper.readTree((String)response.readEntity(String.class));
			List<LastTrade> lastTradeList=new LinkedList<>();
			for (JsonNode node : nodes.get("trade_data"))
			{
				lastTradeList.add(mapper.readValue(node.toString(), LastTrade.class));
			}
			return lastTradeList;
		}
		else
			throw new CoindcxApiException(response.getStatus());
		
	}

	/**
	 * Returns the orderbook as an object of class Orderbook with properly sorted top 20 asks and bids.
	 * @param market Currency Pair
	 * @throws IOException
	 */
	Orderbook getOrderbook(CoindcxCurrencyPair market) throws IOException {
		response = target.path("v1").path("books").path(market.toString().replace("_", "")).request().get();
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(TreeMap.class, Double.class, Double.class);
			JsonNode nodes = mapper.readTree((String)response.readEntity(String.class));
			TreeMap<Double, Double> askMap = (TreeMap)mapper.readValue(nodes.get("asks").toString(), type);
			TreeMap<Double, Double> bidMap = (TreeMap)mapper.readValue(nodes.get("bids").toString(), type);
			List<Asks> askList = new LinkedList();
			List<Bids> bidList = new LinkedList();
			for (Map.Entry<Double, Double> asks : askMap.entrySet())
			{
				askList.add(new Asks(((Double)asks.getValue()).doubleValue(), ((Double)asks.getKey()).doubleValue()));
			}
			for (Map.Entry<Double, Double> bids : bidMap.entrySet())
			{
				bidList.add(new Bids(((Double)bids.getValue()).doubleValue(), ((Double)bids.getKey()).doubleValue()));
			}
			Collections.sort(bidList, new Comparator<Bids>() {

				public int compare(Bids b1, Bids b2) {
					return Double.valueOf(b2.getPrice()).compareTo(Double.valueOf(b1.getPrice()));
				}
			});
			return new Orderbook(askList.subList(0, 20), bidList.subList(0, 20), System.currentTimeMillis());

		}
		else
			throw new CoindcxApiException(response.getStatus());
	}


	/**
	 * Saves the list of currently tradeable markets to res/tradeable_pairs
	 * @throws IOException
	 */
	void saveMarkets() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://api.coindcx.com/exchange/v1").path("markets").request().get();
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode nodes = mapper.readTree((String)response.readEntity(String.class));
			BufferedWriter writer = Files.newBufferedWriter(Paths.get("res/tradeable_pairs", new String[0]), Charsets.UTF_8, new OpenOption[0]);
			for (JsonNode node : nodes)
			{
				if (node.asText().contains("BTC"))
				{
					writer.write(node.asText().split("BTC")[0] + "/BTC");
				}
				else
				{
					writer.write(node.asText().split("ETH")[0] + "/ETH");
				}

				writer.append("\n");
				writer.flush();
			}
			writer.write("BTC/USDT");
			writer.append("\n");
			writer.flush();
			writer.write("ETH/USDT");
			writer.flush();
			writer.close();
		}
		else
			throw new CoindcxApiException(response.getStatus());
	}

	/**
	 * Returns the balance of currency whose code is passed as the argument.
	 * @param currency 
	 * @return
	 * @throws IOException
	 */
	Balance getBalance(String currency) throws IOException
	{
		JSONObject json=new JSONObject().put("timestamp", System.currentTimeMillis()/1000);
		System.out.println(json);
		Response response=target.path("v1").path("users").path("balances").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(json.toString())).header("Content-Type", "application/json").post(Entity.entity(json.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode nodes=mapper.readTree(response.readEntity(String.class));
			Map<String,Balance> balanceMap=new HashMap<>();
			for(JsonNode node:nodes)
			{
				balanceMap.put(node.get("currency").asText(), mapper.readValue(node.toString(), Balance.class));
			}

			return balanceMap.get(currency.toUpperCase());
		}
		else 
			throw new CoindcxApiException(response.getStatus());
	}

	/**
	 * Places the order on the specified market.
	 * @param market Market in which order is to be placed
	 * @param quantity Amount of currency to be traded
	 * @param price	Price per unit
	 * @param type Type of trade - Buy or Sell
	 * @param order_type Type of order - Market or Limit
	 * @return
	 * @throws IOException
	 */
	Order placeOrder(CoindcxCurrencyPair market,double quantity,double price,Order.Side type,Order.OrderType order_type) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("market", market.getCounter()+market.getBase());
		if(quantity>=market.getMinimumOrder())
		{
			request.put("total_quantity", quantity);
			if(order_type.equals(Order.OrderType.LIMIT_ORDER))
				request.put("price_per_unit", price);
			request.put("side", type.toString().toLowerCase());
			request.put("order_type", order_type.toString().toLowerCase());
			request.put("timestamp", System.currentTimeMillis()/1000);
			Response response=target.path("v1").path("orders").path("create").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
			if(response.getStatus()==200)
			{
				JsonNode node=mapper.readTree(response.readEntity(String.class));
				Order o=mapper.readValue(node.get("orders").get(0).toString(), Order.class);
				return o;
			}
			else
				throw new CoindcxApiException(response.getStatus());
		}
		else
			throw new IllegalArgumentException("Quantity is less than minimum quantity for this market");
	}

	/**Returns the status of the order as an object of tyoe Order.
	 * @param id Alphanumeric ID of the order.
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	Order getOrderStatus(String id) throws JsonProcessingException, IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		request.put("id", id);
		Response response=target.path("v1").path("orders").path("status").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		if(response.getStatus()!=404)
		{
			Order o=mapper.readValue(response.readEntity(String.class), Order.class);
			return o;
		}
		else
			throw new CoindcxApiException(response.getStatus());
	}

	/**
	 * Returns an array of Order which denotes all the active orders for this market.
	 * @param market Currency pair.
	 * @param side buy/sell orders
	 * @return
	 * @throws IOException
	 */
	Order[] getActiveOrders(CoindcxCurrencyPair market,Order.Side... side) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		request.put("market", market.getCounter()+market.getBase());
		System.out.println(request);
		if(side!=null)
			request.put("side", side.toString().toLowerCase());
		Response response=target.path("v1").path("orders").path("active_orders").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode node=mapper.readTree(response.readEntity(String.class));
			Order[] o=mapper.readValue(node.toString(), Order[].class);
			return o;
		}
		else
			throw new CoindcxApiException(response.getStatus());

	}

	/**
	 * Returns the trading activity of the user as an array of Order.
	 * @param limit 0 if the last 500 trades are to be returned. Any other limit to be specified otherwise.
	 * @param from_id
	 * @return
	 * @throws IOException
	 */
	Order[] getAccountTradingHistory(int limit,String from_id) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		if(limit!=0)
			request.put("limit", limit);
		if(from_id!=null)
			request.put("from_id", from_id);
		System.out.println(request);
		Response response=target.path("v1").path("orders").path("trade_history").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		JSONArray node=new JSONArray(response.readEntity(String.class));
		for(Object ob:node)
		{
			JSONObject object=(JSONObject)ob;
			object.put("market", object.get("symbol").toString());
			object.remove("symbol");
			System.out.println(object);
		}
		/*node.put("market", node.get("symbol").toString());
		node.remove("symbol");*/
		Order[] o=mapper.readValue(node.toString(), Order[].class);
		return o;
	}

	/**
	 * Returns a count of all the active orders. Can also be obtained by counting entries in the array returned by getActiveOrders()
	 * @param market
	 * @param side
	 * @return
	 * @throws IOException
	 */
	int getActiveOrdersCount(CoindcxCurrencyPair market,Order.Side...side) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		request.put("market", market.getCounter()+market.getBase());
		if(side!=null)
			request.put("side", side[0].toString().toLowerCase());
		System.out.println(request);
		Response response=target.path("v1").path("orders").path("active_orders_count").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode node=mapper.readTree(response.readEntity(String.class));
			return node.get("count").asInt();
		}
		else 
			throw new CoindcxApiException(response.getStatus());		
	}

	/** Cancel all orders for the specified market
	 * @param market
	 * @param side
	 * @return
	 * @throws IOException
	 */
	boolean cancelOrder(CoindcxCurrencyPair market,Order.Side...side) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		request.put("market", market.getCounter()+market.getBase());
		if(side.length!=0)
			request.put("side", side.toString().toLowerCase());
		Response response=target.path("v1").path("orders").path("cancel_all").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode node=mapper.readTree(response.readEntity(String.class));
			System.out.println(node);
			if(node.get("message").asText().equals("success"))
				return true;
			else
				return false;
		}
		else
			throw new CoindcxApiException(response.getStatus());
		
	}
	
	/** Cancel the order with the specified id.
	 * @param id
	 * @return
	 * @throws IOException
	 */
	boolean cancelOrder(String id) throws IOException
	{
		JSONObject request=new JSONObject();
		request.put("timestamp", System.currentTimeMillis()/1000);
		request.put("id", id);	
		Response response=target.path("v1").path("orders").path("cancel").request().header("X-AUTH-APIKEY", CoindcxRequestGenerator.key).header("X-AUTH-SIGNATURE", CoindcxRequestGenerator.signatureGenerator(request.toString())).post(Entity.entity(request.toString(), MediaType.APPLICATION_JSON));
		response.bufferEntity();
		if(response.getStatus()==200)
		{
			JsonNode node=mapper.readTree(response.readEntity(String.class));
			if(node.get("message").asText().equals("success"))
				return true;
			else
				return false;
		}
		else
			throw new CoindcxApiException(response.getStatus());
		
	}
}
