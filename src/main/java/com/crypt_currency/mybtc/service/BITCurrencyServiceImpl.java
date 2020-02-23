package com.crypt_currency.mybtc.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.crypt_currency.mybtc.model.BITCurrency;
import com.crypt_currency.mybtc.model.BITCurrencyDetails;
import com.crypt_currency.mybtc.model.BITCurrencyQuote;
import com.crypt_currency.mybtc.model.BITCurrencySymbol;
import com.crypt_currency.mybtc.model.BitCurrencyList;

@Service
public class BITCurrencyServiceImpl {

	Logger logger = LoggerFactory.getLogger(BITCurrencyServiceImpl.class);
	
	@Value("${btc.symbols}")
	private String allowedSymbols;

	@Value("${btc.currency_api:https://api.hitbtc.com/api/2/public/currency}")
	private String currencyApi;

	@Value("${btc.symbol_api:https://api.hitbtc.com/api/2/public/symbol}")
	private String symbolApi;

	@Value("${btc.quote_api:https://api.hitbtc.com/api/2/public/ticker}")
	private String quoteApi;

	private Set<String> allowedSymbolsList;
	
	@Autowired
	private RestTemplate template;
	
	private Map<String, BITCurrencyDetails> currencyDetailMap = new HashMap<>(); 
	private Map<String, BITCurrencySymbol> symbolDetailMap = new HashMap<>();
	
	@PostConstruct
	public void postConstruct() {
		if(allowedSymbols !=null) {
			allowedSymbolsList= new HashSet<String>(Arrays.asList(allowedSymbols.split("\\s*,\\s*")));
		}
	}
	
	public BitCurrencyList getQuotes() {
		List<BITCurrency> list = allowedSymbolsList
			.stream()
			.map(this::getCurrencyQuote)
			.collect(Collectors.toList());
		
		BitCurrencyList bList = new BitCurrencyList();
		bList.setCurrencies(list);
		return bList;
	}
	
	@Retryable(value = RestClientException.class, maxAttempts = 2, backoff = @Backoff(delay = 500))
	public BITCurrency getCurrencyQuote(String quoteSymbol) {
		
		if(!allowedSymbolsList.contains(quoteSymbol)) {
			throw new IllegalArgumentException("Requested symbol is not valid or not supported");
		}
		
		BITCurrency bitCurrency = new BITCurrency();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		CompletableFuture<ResponseEntity<BITCurrencyQuote>> futureQuoteResponse = 
															CompletableFuture.supplyAsync(()->template.exchange(quoteApi+"/{currency}", HttpMethod.GET, requestEntity, BITCurrencyQuote.class, quoteSymbol))
															.handle(this::handleException);
		
		BITCurrencyDetails currencyDetail = currencyDetailMap.computeIfAbsent(quoteSymbol.substring(0,3), this::getCurrencyDetails);

		if(currencyDetail != null) {
			bitCurrency.setId(currencyDetail.getId());
			bitCurrency.setFullName(currencyDetail.getFullName());	
		}

		BITCurrencySymbol symbolDetail = symbolDetailMap.computeIfAbsent(quoteSymbol, this::getSymbolDetails);
		
		if(symbolDetail != null) {
			bitCurrency.setFeeCurrency(symbolDetail.getFeeCurrency());
		}
		
		BITCurrency currency = futureQuoteResponse
								.handle(this::handleException)
								.thenApply(response->applyQuoteDetails(bitCurrency, response.getBody()))
								.join();
		return currency;
	}

	@Retryable(value = RestClientException.class, maxAttempts = 3, backoff = @Backoff(delay = 500))
	@Cacheable("currency")
	private BITCurrencyDetails getCurrencyDetails(String currency) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
 
		return CompletableFuture.supplyAsync(()-> 	template.exchange(currencyApi+"/{currency}", HttpMethod.GET, requestEntity, BITCurrencyDetails.class, currency.substring(0,3))	)
		.handle(this::handleException)
		.thenApply(response->response.getBody())
		.join();
	}

	@Recover
	private BITCurrencyDetails recoverCurrencyDetails(RestClientException exception, String currency) {
		BITCurrencyDetails details= new BITCurrencyDetails();
		details.setId("-1");
		details.setFullName("default");	
		return details;
	}
	
	@Retryable(value = RestClientException.class, maxAttempts = 3, backoff = @Backoff(delay = 500))
	@Cacheable("symbol")
	private BITCurrencySymbol getSymbolDetails(String symbol) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

		return  
				CompletableFuture.supplyAsync(()->template.exchange(symbolApi+"/{symbol}", HttpMethod.GET, requestEntity, BITCurrencySymbol.class, symbol))
				.handle(this::handleException)
				.thenApply(response->response.getBody())
				.join();
	}

	@Recover
	private BITCurrencySymbol recoverSymbolDetails(RestClientException exception, String symbol) {
		BITCurrencySymbol currency = new BITCurrencySymbol();
		currency.setFeeCurrency("USD");
		return currency;
	}
	
	private BITCurrency applyQuoteDetails(BITCurrency bitCurrency, BITCurrencyQuote currencyQuote) {
		bitCurrency.setAsk(currencyQuote.getAsk());
		bitCurrency.setBid(currencyQuote.getBid());
		bitCurrency.setLast(currencyQuote.getLast());
		bitCurrency.setOpen(currencyQuote.getOpen());
		bitCurrency.setLow(currencyQuote.getLow());
		bitCurrency.setHigh(currencyQuote.getHigh());
		return bitCurrency;
	}

	private <R> R handleException(R response, Throwable exception) {
		if(exception != null) {
			logger.error(exception.getMessage(), exception);
		}
		return response;
	}
}
