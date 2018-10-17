package dto.userdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Balance {
	double balance;
	double locked_balance;
	@JsonCreator
	public Balance(@JsonProperty("balance")double balance,@JsonProperty("locked_balance") double locked_balance) {
		this.balance = balance;
		this.locked_balance = locked_balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getLocked_balance() {
		return locked_balance;
	}
	
	@Override
	public String toString() {
		return "Balance [balance=" + balance + ", locked_balance=" + locked_balance + "]";
	}
}
