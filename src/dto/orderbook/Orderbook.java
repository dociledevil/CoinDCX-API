package dto.orderbook;

import java.util.List;

/**
 * @author dociledevil
 *Represents an orderbook entry at an instant in time.
 */
public class Orderbook {
	/**
	 *List of top Asks 
	 */
	List<Asks> asks;
	/**
	 *List of top Bids 
	 */
	List<Bids> bids;
	/**
	 *Time at which the orderbook snapshot was generated. 
	 */
	long timestamp;

	public Orderbook(List<Asks> asks, List<Bids> bids, long timestamp) {
		this.asks = asks;
		this.bids = bids;
		this.timestamp = timestamp;
	}

	/**
	 * @return List of top Ask entries.
	 */
	public List<Asks> getAsks() {
		return asks;
	}
	
	/**
	 * @return List of top Bid entries.
	 */
	public List<Bids> getBids() {
		return bids;
	}

	/**
	 * @return Timestamp of the orderbook in milliseconds since epoch.
	 */
	public long getTimestamp() {
		return timestamp;
	}

	public String toString()
	{
		return "Orderbook [asks=" + asks + ", bids=" + bids + ", timestamp=" + timestamp + "]";
	}
}