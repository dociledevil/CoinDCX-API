# CoinDCX API Mapping
Its a straightforward mapping of CoinDCX REST API in Java. It uses Jersey client for interaction with the API and Jackson for mapping JSON responses to Java objects. It allows for use of API methods for algorithmic trading including arbitrage.

# Motivations
There is no Java based mapping of CoinDCX's API, one of the first and only exchanges that open up their API for getting orderbooks and placing orders. It is a part of an overall project that deals with leveraging arbitrage in the Indian crypto market

# Private API Calls
Place the API-Key and the API-Secret as first and second lines in he file res/key.(Its imperative that API-Key is line number 1 and API-Secret is line number 2.) 

# Example
The project mainly uses the Data Transfer Object pattern where the object of class CoinDCXDTO can be used to call methods to get data from the API. For eg.

To get the latest orderbook entry, 

```Java
		CoinDCXDTO dto=new CoinDCXDTO();
		Orderbook orderbook=dto.getOrderbook(CoindcxCurrencyPair.ETH_BTC);
		List<Asks> asks=orderbook.getAsks();
		List<Bids> bids=orderbook.getBids();
		System.out.println("Top ask is "+asks.get(0));
		System.out.println("Top bid is "+bids.get(0));
```
And to get the most recent trade 

```Java
		List<LastTrade> lastTrade=dto.getLastTrade(CoindcxCurrencyPair.BAT_ETH);
		LastTrade latestTrade=lastTrade.get(0);
		if(latestTrade.isMaker())
		{
			System.out.println("Most recent trade was a sell order for quantity "+latestTrade.getVolume()+" priced at "+latestTrade.getPrice());
		}
		else
		{
			System.out.println("Most recent trade was a buy order for quantity "+latestTrade.getVolume()+" priced at "+latestTrade.getPrice());
		}
```

To get the balance of a currency eg. Ripple, 

```Java
	System.out.println(dto.getBalance("XRP");
```

To place a market order for a currenct eg. Tron, 

```Java
	System.out.println(dto.placeOrder(dto.placeOrder(CoindcxCurrencyPair.TRX_BTC, 25, 0, Order.Side.BUY, Order.OrderType.MARKET_ORDER)));
```
For a limit order, replace the 0 as price_per_unit	with your value.

The call to placeOrder() returns an object of type Order which can be queried for order details.

For more info. about methods of CoinDCXDTO, see the JavaDoc.

# Disclaimer
The backend of CoinDCX's REST API is dodgy and at times returns erroneous results. Check with their telegram 
channel in case such a situation arises. 









		
