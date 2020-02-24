package com.crypt_currency.mybtc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.crypt_currency.mybtc.model.BITCurrency;
import com.crypt_currency.mybtc.model.BitCurrencyList;
import com.crypt_currency.mybtc.service.BITCurrencyService;

@RestController
public class BITCurrencyController {
	Logger logger = LoggerFactory.getLogger(BITCurrencyController.class);

	@Autowired
	BITCurrencyService service;
	
	@GetMapping(value = "/currency/{symbol}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getBitCurrencyQuote(@PathVariable String symbol) {
		
		if(symbol ==null || symbol.isEmpty()) {
			return new ResponseEntity<String>("symbol cannot be empty", HttpStatus.BAD_REQUEST);
		}
		
		if(symbol.length() != 6) {
			return new ResponseEntity<String>("Currency symbol must be 6 character length.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			BITCurrency currency = service.getCurrencyQuote(symbol);
			return new ResponseEntity<BITCurrency>(currency, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/currency/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getAllCurrencyQuotes() {
		try {
			BitCurrencyList list = service.getQuotes();
			return new ResponseEntity<BitCurrencyList>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
