package com.crypt_currency.mybtc.model;

public class BITCurrencyDetails {
	private String id;
	private String fullName;
	private boolean crypto;	
	private boolean payinEnabled;
	private boolean payinPaymentId;
	private boolean payinConfirmations;
	private boolean payoutEnabled;
	private boolean payoutIsPaymentId;
	private boolean transferEnabled;
	private boolean delisted;
	private double payoutFee;
	
	public BITCurrencyDetails() {}
	public BITCurrencyDetails(String id) {this.id = id;}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public boolean isCrypto() {
		return crypto;
	}
	public void setCrypto(boolean crypto) {
		this.crypto = crypto;
	}
	public boolean isPayinEnabled() {
		return payinEnabled;
	}
	public void setPayinEnabled(boolean payinEnabled) {
		this.payinEnabled = payinEnabled;
	}
	public boolean isPayinPaymentId() {
		return payinPaymentId;
	}
	public void setPayinPaymentId(boolean payinPaymentId) {
		this.payinPaymentId = payinPaymentId;
	}
	public boolean isPayinConfirmations() {
		return payinConfirmations;
	}
	public void setPayinConfirmations(boolean payinConfirmations) {
		this.payinConfirmations = payinConfirmations;
	}
	public boolean isPayoutEnabled() {
		return payoutEnabled;
	}
	public void setPayoutEnabled(boolean payoutEnabled) {
		this.payoutEnabled = payoutEnabled;
	}
	public boolean isPayoutIsPaymentId() {
		return payoutIsPaymentId;
	}
	public void setPayoutIsPaymentId(boolean payoutIsPaymentId) {
		this.payoutIsPaymentId = payoutIsPaymentId;
	}
	public boolean isTransferEnabled() {
		return transferEnabled;
	}
	public void setTransferEnabled(boolean transferEnabled) {
		this.transferEnabled = transferEnabled;
	}
	public boolean isDelisted() {
		return delisted;
	}
	public void setDelisted(boolean delisted) {
		this.delisted = delisted;
	}
	public double getPayoutFee() {
		return payoutFee;
	}
	public void setPayoutFee(double payoutFee) {
		this.payoutFee = payoutFee;
	}
	
	
	
	
}
