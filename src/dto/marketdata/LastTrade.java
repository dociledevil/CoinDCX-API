package dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dociledevil
 * Class representing a single last trade as returned by API Call.
 */
/**
 * @author dociledevil
 *
 */
public class LastTrade {
	
	/**
	 *Whether the buyer is market maker or not 
	 */
	boolean maker;
	/**
	 *Trade price 
	 */
	double price;
	/**
	 *Quantity 
	 */
	double volume;
	
	/**
	 *Timestamp of the trade(in millis since epoch). 
	 */
	long timestamp;
	
	/**
	 * The annotations @JsonCreator and @JsonProperty ensure appropriate deserialization. 
	 * @param maker
	 * @param price
	 * @param volume
	 * @param timestamp
	 */
	@JsonCreator
	public LastTrade(@JsonProperty("m") boolean maker, @JsonProperty("p") double price, @JsonProperty("q") double volume, @JsonProperty("T") long timestamp) {
		this.maker = maker;
		this.price = price;
		this.volume = volume;
		this.timestamp = timestamp;
	}

	public String toString() {
		return "LastTrade [maker=" + maker + ", price=" + price + ", volume=" + volume + ", timestamp=" + timestamp + "]";
	}

	
	/**
	 * @return Whether last trade was a buy or a sell order.
	 */
	public boolean isMaker() {
		return maker;
	}

	/**
	 * @return Price at whoch trade was executed.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return Quantity of last trade.
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * @return Time of last trade in milliseconds since epoch.
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	
}