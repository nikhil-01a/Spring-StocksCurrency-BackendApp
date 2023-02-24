package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Stock;
import com.springrest.springrest.services.QuoteService;

@RestController
@RequestMapping("/stock")
public class QuotesController {
	
	//Making an automatic object for our service layer
	@Autowired
	private QuoteService quoteservice;
	
	//Dummy function just to test
	@GetMapping("/home")
	public String home()
	{
		return "This is a Stock Quotes' Home Page";
		
	}
	
	//Controller to get the conversion of quote from USD to another currency (limiting it to EUR for now)
	//The 'getQuotes()' of QuoteService in 'Service' layer will be contacted from here
	@GetMapping("/conversion")
	public ResponseEntity<List<Stock>> getQuotes(@RequestParam("tickers") List<String> tickers,@RequestParam("currency") String currency)
	{
		return this.quoteservice.getQuotes(tickers, currency);
	}
}