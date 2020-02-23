package com.crypt_currency.mybtc.model;

import java.math.BigDecimal;
import java.util.Date;

public class BITCurrencyQuote {
	
	private BigDecimal ask;
	private BigDecimal bid;
	private BigDecimal last;
	private BigDecimal open;
	private BigDecimal low;
	private BigDecimal high;
	
	private BigDecimal volumeQuote;
	private BigDecimal volume;
	private Date timestamp;
	private String symbol;
	
	private String feeCurrency;
	
	public BITCurrencyQuote() {
	}
	
	public BigDecimal getAsk() {
		return ask;
	}
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	public BigDecimal getBid() {
		return bid;
	}
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	public BigDecimal getLast() {
		return last;
	}
	public void setLast(BigDecimal last) {
		this.last = last;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public String getFeeCurrency() {
		return feeCurrency;
	}
	public void setFeeCurrency(String feeCurrency) {
		this.feeCurrency = feeCurrency;
	}

	public BigDecimal getVolumeQuote() {
		return volumeQuote;
	}

	public void setVolumeQuote(BigDecimal volumeQuote) {
		this.volumeQuote = volumeQuote;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
