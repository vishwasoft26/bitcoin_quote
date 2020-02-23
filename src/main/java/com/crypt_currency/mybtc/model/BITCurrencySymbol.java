package com.crypt_currency.mybtc.model;

public class BITCurrencySymbol {
	
	private String id;
	private String feeCurrency;
	private String baseCurrency;
	private String quoteCurrency;
	
	private double quantityIncrement;
	private double tickSize;
	private double takeLiquidityRate;
	private double provideLiquidityRate;
	
	public BITCurrencySymbol() {}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFeeCurrency() {
		return feeCurrency;
	}


	public void setFeeCurrency(String feeCurrency) {
		this.feeCurrency = feeCurrency;
	}


	public String getBaseCurrency() {
		return baseCurrency;
	}


	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}


	public String getQuoteCurrency() {
		return quoteCurrency;
	}


	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}


	public double getQuantityIncrement() {
		return quantityIncrement;
	}


	public void setQuantityIncrement(double quantityIncrement) {
		this.quantityIncrement = quantityIncrement;
	}


	public double getTickSize() {
		return tickSize;
	}


	public void setTickSize(double tickSize) {
		this.tickSize = tickSize;
	}


	public double getTakeLiquidityRate() {
		return takeLiquidityRate;
	}


	public void setTakeLiquidityRate(double takeLiquidityRate) {
		this.takeLiquidityRate = takeLiquidityRate;
	}


	public double getProvideLiquidityRate() {
		return provideLiquidityRate;
	}


	public void setProvideLiquidityRate(double provideLiquidityRate) {
		this.provideLiquidityRate = provideLiquidityRate;
	}
	
	
}
