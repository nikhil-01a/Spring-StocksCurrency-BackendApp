package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springrest.springrest.entities.Stock;

@Service
@PropertySource("classpath:user.properties") //Overriding application.properties file
public class QuoteServiceimpl implements QuoteService {
	
	/*Creating a RestTemplate object from RestTemplate class of spring to retrieve stock quotes and forex rates using API*/
	@Autowired
	private RestTemplate restTemplate;
	
	//Will take the api key value from user.properties and store it in apiKey
	@Value("${api_key}")
	private String apiKey;
	
	
	/* This will be our final object of type stock to return to the controller which will have the converted quote */														
	List<Stock> convertedstockquote;
	
	@Override
	public ResponseEntity<List<Stock>> getQuotes(List<String> tickers, String currency) {
		
		//Creating an Array list to store all the stocks we will work with, convert their quotes and store them in this list
		//This list will then be returned to controller
		convertedstockquote = new ArrayList<>();
		
		int size = tickers.size();
		int waittime = 0;
		if(size>=3)
		{
			waittime = size*4000;
		}
		
		//Creating a variable string 'ticker', and storing each stock from the user given 'tickers' list in it one stock at a time
		for(String ticker : tickers)
		{	
			//Creating a 'Stock' object for each stock and storing values in it, here we will save name of symbol & currency first
			Stock newstock = new Stock();
			newstock.setSymbol(ticker);
			newstock.setCurrency(currency);
			
			//////////////////////////////Retrieving Stock Quote////////////////////////////////
			//Adding wait time since we can only make 5 API calls within a minute
			try{
				  Thread.sleep(waittime);
			}catch(InterruptedException ex)
			{
				  //do stuff	
			}
			
			//Here we will retrieve the stock based on its symbol using RestTemplate we defined earlier, storing the stock's quote Url into a variable 'stockquoteurl', keeping symbol ticker modifiable with our ticker and apikey to the apikey we have stored
			String stockquoteurl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey;
			
			//Capturing the stock quote url's response into a response entity and then storing it in json format in a json object called 'stockquoteJson'
			ResponseEntity<String> stockquoteResponse = restTemplate.getForEntity(stockquoteurl, String.class);
			JSONObject stockquoteJson = new JSONObject(stockquoteResponse.getBody());
			
			//Retrieving values from the Json object we created, modifying and storing them in our 'newstock'
			JSONObject availableQuote = stockquoteJson.getJSONObject("Global Quote");
			
			newstock.setOpen(availableQuote.getDouble("02. open"));
			newstock.setHigh(availableQuote.getDouble("03. high"));
			newstock.setLow(availableQuote.getDouble("04. low"));
			newstock.setClose(availableQuote.getDouble("05. price"));
			newstock.setVolume(availableQuote.getLong("06. volume"));
			newstock.setLatestTradingDay(availableQuote.getString("07. latest trading day"));
			newstock.setPreviousClose(availableQuote.getDouble("08. previous close"));
			newstock.setChange(availableQuote.getDouble("09. change"));
			newstock.setChangePercent(availableQuote.getString("10. change percent"));
			
			//////////////////////////////Retrieving Forex Rate////////////////////////////////
			//Adding wait time since we can only make 5 API calls within a minute
			try{
				  Thread.sleep(waittime);
			}catch(InterruptedException ex)
			{
				  //do stuff
			}
			
			//Storing forex url
			String forexurl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=" + currency + "&apikey=" + apiKey;
			
			// Retrieving forex response and storing it as json object
			ResponseEntity<String> forexResponse = restTemplate.getForEntity(forexurl, String.class);
			JSONObject forexJson = new JSONObject(forexResponse.getBody());
			
			//Retrieving values from the Json object we created, modifying and storing them in our 'newstock'
			JSONObject liveForexRate = forexJson.getJSONObject("Realtime Currency Exchange Rate");
			
			newstock.setRate(liveForexRate.getDouble("5. Exchange Rate"));
			
			/////////////////////////////Setting Converted Values///////////////////////////////
			
			newstock.setConvertedOpen(newstock.getOpen()*newstock.getRate());
			newstock.setConvertedHigh(newstock.getHigh()*newstock.getRate());
			newstock.setConvertedLow(newstock.getLow()*newstock.getRate());
			newstock.setConvertedClose(newstock.getClose()*newstock.getRate());


			////////////Storing this 'newstock' in our final list 'convertedstockquote'//////////////
			convertedstockquote.add(newstock);
			
		}
		return new ResponseEntity<>(convertedstockquote,HttpStatus.OK);
	}
}