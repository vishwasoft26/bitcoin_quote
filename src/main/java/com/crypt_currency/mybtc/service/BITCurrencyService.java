package com.crypt_currency.mybtc.service;

import com.crypt_currency.mybtc.model.BITCurrency;
import com.crypt_currency.mybtc.model.BitCurrencyList;

public interface BITCurrencyService {
	public BitCurrencyList getQuotes();
	public BITCurrency getCurrencyQuote(String quoteSymbol);
}
