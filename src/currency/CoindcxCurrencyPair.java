package currency;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;


/**
 * @author dociledevil
 *	Enum representing tradeable pairs on CoinDCX.
 */
public enum CoindcxCurrencyPair
{
	QSP_BTC("QSP","BTC",1.0,8.0,0.0),
	QSP_ETH("QSP","ETH",1.0,8.0,0.0),
	LINK_BTC("LINK","BTC",1.0,8.0,0.0),
	IOST_BTC("IOST","BTC",1.0,8.0,0.0),
	TUSD_ETH("TUSD","ETH",1.0,8.0,0.0),
	XMR_BTC("XMR","BTC",0.001,6.0,3.0),
	CMT_BTC("CMT","BTC",1.0,8.0,0.0),
	CMT_ETH("CMT","ETH",1.0,8.0,0.0),
	REQ_BTC("REQ","BTC",1.0,8.0,0.0),
	FUN_ETH("FUN","ETH",50.0,8.0,0.0),
	REP_BTC("REP","BTC",0.001,6.0,3.0),
	REP_ETH("REP","ETH",0.001,5.0,3.0),
	WABI_BTC("WABI","BTC",1.0,8.0,0.0),
	WABI_ETH("WABI","ETH",1.0,8.0,0.0),
	POWR_BTC("POWR","BTC",1.0,8.0,0.0),
	POWR_ETH("POWR","ETH",1.0,8.0,0.0),
	ELF_BTC("ELF","BTC",1.0,8.0,0.0),
	ELF_ETH("ELF","ETH",1.0,8.0,0.0),
	CTXC_BTC("CTXC","BTC",1.0,8.0,0.0),
	DENT_BTC("DENT","BTC",1.0,8.0,0.0),
	LRC_BTC("LRC","BTC",1.0,8.0,0.0),
	LINK_ETH("LINK","ETH",1.0,8.0,0.0),
	IOST_ETH("IOST","ETH",1.0,8.0,0.0),
	THETA_BTC("THETA","BTC",1.0,8.0,0.0),
	ITC_BTC("ITC","BTC",1.0,8.0,0.0),
	MTN_BTC("MTN","BTC",1.0,8.0,0.0),
	HOT_BTC("HOT","BTC",1.0,8.0,0.0),
	DENT_ETH("DENT","ETH",1.0,8.0,0.0),
	ENG_ETH("ENG","ETH",1.0,7.0,0.0),
	XMR_ETH("XMR","ETH",0.001,5.0,3.0),
	QKC_BTC("QKC","BTC",1.0,8.0,0.0),
	LRC_ETH("LRC","ETH",1.0,8.0,0.0),
	SALT_BTC("SALT","BTC",1.0,7.0,2.0),
	QKC_ETH("QKC","ETH",1.0,8.0,0.0),
	MANA_ETH("MANA","ETH",1.0,8.0,0.0),
	CND_BTC("CND","BTC",1.0,8.0,0.0),
	KNC_BTC("KNC","BTC",1.0,8.0,0.0),
	MCO_BTC("MCO","BTC",0.01,6.0,2.0),
	DNT_BTC("DNT","BTC",1.0,8.0,0.0),
	BAT_BTC("BAT","BTC",1.0,8.0,0.0),
	MCO_ETH("MCO","ETH",0.01,6.0,2.0),
	CND_ETH("CND","ETH",1.0,8.0,0.0),
	OMG_ETH("OMG","ETH",0.01,6.0,2.0),
	BAT_ETH("BAT","ETH",1.0,8.0,0.0),
	EOS_ETH("EOS","ETH",0.01,6.0,2.0),
	SNT_ETH("SNT","ETH",1.0,8.0,0.0),
	ICX_ETH("ICX","ETH",0.01,6.0,2.0),
	REQ_ETH("REQ","ETH",1.0,8.0,0.0),
	LTC_ETH("LTC","ETH",0.001,5.0,3.0),
	KNC_ETH("KNC","ETH",1.0,8.0,0.0),
	ETH_BTC("ETH","BTC",0.001,6.0,3.0),
	LTC_BTC("LTC","BTC",0.01,6.0,2.0),
	OMG_BTC("OMG","BTC",0.01,6.0,2.0),
	ICX_BTC("ICX","BTC",0.01,7.0,2.0),
	TRX_BTC("TRX","BTC",1.0,8.0,0.0),
	SNT_BTC("SNT","BTC",1.0,8.0,0.0),
	EOS_BTC("EOS","BTC",0.01,7.0,2.0),
	CVC_ETH("CVC","ETH",1.0,8.0,0.0),
	CVC_BTC("CVC","BTC",1.0,8.0,0.0),
	XRP_BTC("XRP","BTC",5.0,8.0,0.0),
	DGD_ETH("DGD","ETH",0.001,5.0,3.0),
	STORM_ETH("STORM","ETH",50.0,8.0,0.0),
	STORM_BTC("STORM","BTC",50.0,8.0,0.0),
	DGD_BTC("DGD","BTC",0.001,6.0,3.0),
	GNT_ETH("GNT","ETH",5.0,8.0,0.0),
	BLZ_ETH("BLZ","ETH",2.0,8.0,0.0),
	GNT_BTC("GNT","BTC",2.0,8.0,0.0),
	XRP_ETH("XRP","ETH",5.0,8.0,0.0),
	BLZ_BTC("BLZ","BTC",2.0,8.0,0.0),
	ZIL_BTC("ZIL","BTC",11.0,8.0,0.0),
	ZIL_ETH("ZIL","ETH",11.0,8.0,0.0),
	ZRX_ETH("ZRX","ETH",2.0,8.0,0.0),
	ZRX_BTC("ZRX","BTC",2.0,8.0,0.0),
	FUN_BTC("FUN","BTC",50.0,8.0,0.0),
	SUB_BTC("SUB","BTC",1.0,8.0,0.0),
	MANA_BTC("MANA","BTC",1.0,8.0,0.0),
	TRX_ETH("TRX","ETH",1.0,8.0,0.0),
	SUB_ETH("SUB","ETH",1.0,8.0,0.0),
	SALT_ETH("SALT","ETH",1.0,6.0,2.0),
	TUSD_BTC("TUSD","BTC",1.0,8.0,0.0),
	THETA_ETH("THETA","ETH",1.0,8.0,0.0),
	PAY_BTC("PAY","BTC",1.0,8.0,0.0),
	HOT_ETH("HOT","ETH",1.0,8.0,0.0),
	ENG_BTC("ENG","BTC",1.0,8.0,0.0), 
	BTC_USDT("BTC", "USDT", 0.0D, 0.0D, 0.0D), 
	ETH_USDT("ETH", "USDT", 0.0D, 0.0D, 0.0D);

	/**
	 *Counter currency 
	 */
	private final String counter;
	/**
	 *Base currency 
	 */
	private final String base;
	/**
	 *Minimum order allowed
	 */
	private final double minimumOrder;
	/**
	 *Maximum precision allowed for base currency
	 */
	private final double baseCurrencyPrecision;
	
	/**
	 *Maximum precision allowed for quote/counter currency 
	 */
	private final double targetCurrencyPrecision;
	/**
	 * To make requests with new currency pairs which are not part of the above mentioned constants, this constructor is used.
	 * @param counter
	 * @param base
	 * @param minimumOrder
	 * @param baseCurrencyPrecision
	 * @param targetCurrencyPrecision
	 */
	private CoindcxCurrencyPair(String counter, String base, double minimumOrder, double baseCurrencyPrecision, double targetCurrencyPrecision) {
		this.counter = counter;
		this.base = base;
		this.minimumOrder = minimumOrder;
		this.baseCurrencyPrecision = baseCurrencyPrecision;
		this.targetCurrencyPrecision = targetCurrencyPrecision;
	}
	
	/**
	 * @return Counter/Quote currency
	 */
	public String getCounter(){ 
		return counter; 
	}

	/**
	 * @return Base currency
	 */
	public String getBase() {
		return base;
	}

	/**
	 * @return Minimum order allowed
	 */
	public double getMinimumOrder()	{ 
		return minimumOrder; 
	}

	/**
	 * @return Maximum base currency precision
	 */
	public double getBaseCurrencyPrecision() {
		return baseCurrencyPrecision;
	}

	/**
	 * @return Maximum precision allowed in target currency.
	 */
	public double getTargetCurrencyPreceision() { 
		return targetCurrencyPrecision;
	}

	@JsonAnyGetter
	/**
	 * @return Currency pair
	 */
	public String getCoinDcxCurrencyString() { 
		return counter.toUpperCase() + base.toUpperCase(); 
	}

	
	/**
	 * @param pair
	 * @return Currency pair accepted by CoinDCX API.
	 * @throws IOException
	 */
	public static CoindcxCurrencyPair getCoinDcxCurrencyPair(String pair) throws IOException
	{
		List<String> market = Files.readAllLines(Paths.get("res/tradeable_pairs"));
		for (String marketPair : market)
		{
			String readPair = marketPair.split(" ")[0];
			if (pair.toUpperCase().equals(readPair.replace("/", "")))
				return CoindcxCurrencyPair.valueOf(readPair.replaceAll("/","_"));
		}
		return null;
	}
}