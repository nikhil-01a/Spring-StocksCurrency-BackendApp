package com.springrest.springrest.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springrest.springrest.entities.Stock;

public interface QuoteService {
	
	//The getQuotes() in "QuoteServiceimpl" will be contacted from here
	//This abstract method will be called by Controller's getQuotes()
	public ResponseEntity<List<Stock>> getQuotes(List<String> tickers, String currency );

}