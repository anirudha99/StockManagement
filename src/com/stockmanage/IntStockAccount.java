package com.stockmanage;

import java.util.List;

public interface IntStockAccount {
	
	public long valueOf(List<Company> company);
	
	public void buy(String name);
	
	public void sell(String name);
	
	public void save(String filename,String name);
	
	public void printReport();

}
