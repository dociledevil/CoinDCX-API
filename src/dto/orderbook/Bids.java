
package dto.orderbook;

/**
 * @author dociledevil
 *Represents a bid order in the orderbook
 */
public class Bids {
	/**
	 *Quantity of buy order 
	 */
	double volume;
	/**
	 *Price of buy the order 
	 */
	double price;

	public Bids(double volume, double price) {
		this.volume = volume;
		this.price = price;
	}
	/**
	 * @return Quantity of the buy order
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * @return Price of the ask order
	 */
	public double getPrice() {
		return price;
	}

	public String toString()
	{
		return "Bids [volume=" + volume + ", price=" + price + "]";
	}
}