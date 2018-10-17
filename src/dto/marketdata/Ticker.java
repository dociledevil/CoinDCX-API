package dto.marketdata;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import currency.CoindcxCurrencyPair;

public class Ticker
{

	/**
	 *	Currency Pair 
	 */
	CoindcxCurrencyPair pair;
	/**
	 *24 hour change. 
	 */
	BigDecimal change;
	/**
	 * 24 hour low
	 */
	BigDecimal low;
	/**
	 *24 hour high 
	 */
	BigDecimal high;
	/**
	 *Last price 
	 */
	BigDecimal last;
	/**
	 *Highest bid offer in the orderbook 
	 */
	BigDecimal bid;
	/**
	 *Lowest ask offer in the orderbook 
	 */
	BigDecimal ask;
	
	/**
	 *Timestamp of the last trade in seconds since epoch. 
	 */
	long timestamp;

	public Ticker(CoindcxCurrencyPair pair, BigDecimal change, BigDecimal low, BigDecimal high, BigDecimal last, BigDecimal bid, BigDecimal ask, long timestamp)
	{
		this.pair = pair;
		this.change = change;
		this.low = low;
		this.high = high;
		this.last = last;
		this.bid = bid;
		this.ask = ask;
		this.timestamp = timestamp;
	}


	@JsonCreator
	public Ticker(@JsonProperty("market") String market, @JsonProperty("change_24_hour") double change, @JsonProperty("low") double low, @JsonProperty("high") double high, @JsonProperty("last_price") double last, @JsonProperty("bid") double bid, @JsonProperty("ask") double ask, @JsonProperty("timestamp") long timestamp)
			throws IOException
	{
		pair = CoindcxCurrencyPair.getCoinDcxCurrencyPair(market);
		this.change = BigDecimal.valueOf(change);
		this.low = BigDecimal.valueOf(low);
		this.high = BigDecimal.valueOf(high);
		this.last = BigDecimal.valueOf(last);
		this.bid = BigDecimal.valueOf(bid);
		this.ask = BigDecimal.valueOf(ask);
		this.timestamp = timestamp;
	}
	
	public String toString()
	{
		return "Ticker [pair=" + pair + ", change=" + change + ", low=" + low + ", high=" + high + ", last=" + last + ", bid=" + bid + ", ask=" + ask + ", timestamp=" + timestamp + "]";
	}
	
	/**
	 * @return Pair 
	 */
	public CoindcxCurrencyPair getPair() {
		return pair;
	}

	/**
	 * @return 24 hour change
	 */
	public BigDecimal getChange() {
		return change;
	}
	
	/**
	 * @return 24 hour low.
	 */
	public BigDecimal getLow() {
		return low;
	}

	
	/**
	 * @return  24 hour high
	 */
	public BigDecimal getHigh() {
		return high;
	}

	
	/**
	 * @return Last trade price.
	 */
	public BigDecimal getLast() {
		return last;
	}

	/**
	 * @return  Highest bid
	 */
	public BigDecimal getBid() {
		return bid;
	}

	/**
	 * @return Lowest ask
	 */
	public BigDecimal getAsk() {
		return ask;
	}

	/**
	 * @return Timestamp at which ticker is generated
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	
}