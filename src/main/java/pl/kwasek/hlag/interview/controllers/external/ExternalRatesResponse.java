package pl.kwasek.hlag.interview.controllers.external;

import java.util.Map;

public class ExternalRatesResponse {

	//	{
//	  "amount": 1,
//	  "base": "EUR",
//	  "date": "2020-10-29",
//	  "rates": {
//	    "USD": 1.1704
//	  }
//	}
	private Double amount;
	private String base;
	private String date;
	private Map<String, Double> rates;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	
	
}
