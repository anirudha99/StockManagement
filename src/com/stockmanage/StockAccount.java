package com.stockmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StockAccount implements IntStockAccount {

	String name;
	double price;
	long noOfShare;
	double totalValue;
	String stockSymbol;

	Scanner sc = new Scanner(System.in);


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getNoOfShare() {
		return noOfShare;
	}

	public void setNoOfShare(long noOfShare) {
		this.noOfShare = noOfShare;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	StockAccount(String name, double price, long noOfShare, String stockSymbol) {
		this.name = name;
		this.price = price;
		this.noOfShare = noOfShare;
		this.stockSymbol = stockSymbol;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "StockAccount [name = " + name + ", price = " + price + ", noOfShare = " + noOfShare + ", totalValue = "
				+ totalValue + ", stockSymbol = " + stockSymbol + "]";
	}


	public static List<Company> companyList = new ArrayList<>();
	public static List<Customer> customerList = new ArrayList<>();
	public static List<Transaction> transactionList = new ArrayList<>();
	//	ObjectMapper mapper = new ObjectMapper();
	Date date = new Date();
	long currentAmount;

	public long valueOf( List<Company> company) {
		long value = totalValuesOfShares(company);
		return value;
	}

	public long totalValuesOfShares(List<Company> company) {
		long totalShares = 0;
		for (Company c : company) {
			long share = c.getSharesAvailable();
			long price = c.getPricePerShare();
			totalShares += (share * price);
		}
		return totalShares;
	}

	@Override
	public void buy(String name) {
		System.out.println("\n\tEnter the symbol");
		String symbol = sc.next();
		System.out.println("\n\tEnter the Amount");
		long amount = sc.nextInt();
		Transaction transaction = new Transaction();

		int symbolEqual = 0;
		int amountSmall = 0;
		for (Company c : companyList) {
			if (c.getCompany().equals(symbol)) {
				if (amount >= c.getPricePerShare()) {
					amountSmall++;
					if (!customerList.isEmpty()) {
						for (Customer customerLoop : customerList) {
							currentAmount=customerLoop.getAmount();
							if (customerLoop.getSymbol().equals(symbol)) {
								amountSmall++;
								symbolEqual++;
								customerLoop.setAmount(customerLoop.getAmount() - amount);
								customerLoop.setShares(customerLoop.getShares() + (amount / c.getPricePerShare()));
							}
						}
					}

					if(symbolEqual==0) {
						amountSmall++;
						Customer customer = new Customer();
						customer.setAmount(currentAmount - amount);
						customer.setSymbol(symbol);
						customer.setShares(amount / c.getPricePerShare());
						customerList.add(customer);
					}
					System.out.println("\n\t\t\tTransaction Started");
					transaction.setBuySell("Buy");
					transaction.setName(name);
					transaction.setSymbol(symbol);
					transaction.setDate(date.toString());
					transactionList.add(transaction);
					System.out.println("\n\t\t\tTransaction Stopped");
				}
			}
		}
		if (amountSmall == 0) {
			System.out.println("\n\t\t\tYour Balance is low\n\t\t\tPlease Add money");
		}
	}

	@Override
	public void sell(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String filename, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printReport() {
		// TODO Auto-generated method stub

	}

}
