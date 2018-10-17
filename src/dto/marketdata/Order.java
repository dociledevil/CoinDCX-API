package dto.marketdata;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import currency.CoindcxCurrencyPair;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Order {

	public static enum Side
	{
		BUY,SELL;
	}
	public static enum OrderType
	{
		MARKET_ORDER,LIMIT_ORDER
	}
	String id,status;
	CoindcxCurrencyPair market;
	double total_quantity,remaining_quantity,fee_amount,fee;
	double price_per_unit,price;
	Side side;
	OrderType type;

	@JsonCreator
	public Order(@JsonProperty("id")String id,@JsonProperty("status") String status, @JsonProperty("market")String market, @JsonProperty("total_quantity")double total_quantity, @JsonProperty("remaining_quantity")double remaining_quantity,
			@JsonProperty("fee_amount")double fee_amount,@JsonProperty("fee") double fee, @JsonProperty("price_per_unit")double price_per_unit, @JsonProperty("side")String side, @JsonProperty("order_type")String type,@JsonProperty("price")double price) throws IOException {
		super();
		this.id = id;
		this.status = status;
		this.market = CoindcxCurrencyPair.getCoinDcxCurrencyPair(market);
		this.total_quantity = total_quantity;
		this.remaining_quantity = remaining_quantity;
		this.fee_amount = fee_amount;
		this.fee = fee;
		this.price_per_unit = price;
		if(side!=null)
			this.side = Side.valueOf(side.toUpperCase());
		else
			this.side=null;
		if(type!=null)
			this.type = OrderType.valueOf(type.toUpperCase());
		else
			type=null;
		if(price!=0)
			this.price=price;
		else
			price=0;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", market=" + market + ", total_quantity=" + total_quantity
				+ ", remaining_quantity=" + remaining_quantity + ", fee_amount=" + fee_amount + ", fee=" + fee
				+ ", price=" + price_per_unit + ", side=" + side + ", type=" + type + "]";
	}



	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();

	}

}
