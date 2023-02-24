package com.springrest.springrest.entities;

public class Stock {
	
	private String symbol;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private String latestTradingDay;
	private double previousClose;
	private double change;
	private String changePercent;
	private String currency;
	private double rate;
	private double convertedOpen;
	private double convertedHigh;
	private double convertedLow;
	private double convertedClose;
	
	public Stock(String symbol, double open, double high, double low, double close, long volume,
			String latestTradingDay, double previousClose, double change, String changePercent, String currency,
			double rate, double convertedOpen, double convertedHigh, double convertedLow, double convertedClose) {
		super();
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.latestTradingDay = latestTradingDay;
		this.previousClose = previousClose;
		this.change = change;
		this.changePercent = changePercent;
		this.currency = currency;
		this.rate = rate;
		this.convertedOpen = convertedOpen;
		this.convertedHigh = convertedHigh;
		this.convertedLow = convertedLow;
		this.convertedClose = convertedClose;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getLatestTradingDay() {
		return latestTradingDay;
	}

	public void setLatestTradingDay(String latestTradingDay) {
		this.latestTradingDay = latestTradingDay;
	}

	public double getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public String getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getConvertedOpen() {
		return convertedOpen;
	}

	public void setConvertedOpen(double convertedOpen) {
		this.convertedOpen = convertedOpen;
	}

	public double getConvertedHigh() {
		return convertedHigh;
	}

	public void setConvertedHigh(double convertedHigh) {
		this.convertedHigh = convertedHigh;
	}

	public double getConvertedLow() {
		return convertedLow;
	}

	public void setConvertedLow(double convertedLow) {
		this.convertedLow = convertedLow;
	}

	public double getConvertedClose() {
		return convertedClose;
	}

	public void setConvertedClose(double convertedClose) {
		this.convertedClose = convertedClose;
	}
	
	/*
	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", volume=" + volume + ", latestTradingDay=" + latestTradingDay + ", previousClose=" + previousClose
				+ ", change=" + change + ", changePercent=" + changePercent + ", currency=" + currency + ", rate="
				+ rate + ", convertedOpen=" + convertedOpen + ", convertedHigh=" + convertedHigh + ", convertedLow="
				+ convertedLow + ", convertedClose=" + convertedClose + "]";
	}
	*/
}