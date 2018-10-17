package utils;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import currency.CoindcxCurrencyPair;

public class Playground {

	public static final ObjectMapper mapper = new ObjectMapper();
	public static final Client client = ClientBuilder.newClient();
	public static void main(String[] args) throws IOException {
		System.out.println(CoindcxCurrencyPair.getCoinDcxCurrencyPair("TRXETH"));
		/*WebTarget target = client.target("https://api.coindcx.com/exchange/v1/markets_details");
		Response response=target.request().get();
		JsonNode[] nodes=response.readEntity(JsonNode[].class);
		List<StringBuffer> sbList=new ArrayList<>();
		for(JsonNode node:nodes)
		{
			StringBuffer sb=new StringBuffer();
			sb.append(node.get("target_currency_short_name").asText()).append("_").append(node.get("base_currency_short_name").asText()).append("(").append(node.get("target_currency_short_name").toString()).append(",").append(node.get("base_currency_short_name").toString()).append(",").append(node.get("min_quantity").asDouble()).append(",").append(node.get("base_currency_precision").asDouble()).append(",").append(node.get("target_currency_precision").asDouble()).append(")");
			sbList.add(sb);
		}
		for(StringBuffer sb:sbList)
		{
			System.out.println(sb+",");
		}*/
		
		
		
	}


}
