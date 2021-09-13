package com.stockmanage;

public class Transaction {
	
	private String buySell;
	private String name;
	private String symbol;
	private String date;
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [buySell = " + buySell + ", name = " + name + ", symbol = " + symbol + ", date = " + date + "]";
	}
	
}
