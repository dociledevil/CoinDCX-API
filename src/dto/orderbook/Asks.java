package dto.orderbook;

/**
 * @author dociledevil
 *Represents an ask order in the orderbook.
 */
public class Asks {
	/**
	 *Quantity of sell order 
	 */
	double volume;
	/**
	 *Price of sell the order 
	 */
	double price;

	public Asks(double volume, double price) {
		this.volume = volume;
		this.price = price;
	}

	/**
	 * @return Quantity of the ask order
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
		return "Asks [volume=" + volume + ", price=" + price + "]";
	}
}