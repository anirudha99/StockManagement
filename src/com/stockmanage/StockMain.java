package com.stockmanage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockMain {

	public static Scanner sc = new Scanner(System.in);
	public static JSONArray stockList = new JSONArray();

	public static void main(String[] args) {
		System.out.println("------- Welcome to Stock Management -------");
		List<Stock> list = new ArrayList<>();

		JSONParser jsonParser = new JSONParser();
		try {
			Reader reader = new FileReader("/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StockManagement/data/stock.json");
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray jsonArray = (JSONArray) jsonObject.get("stock");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String name = (String) obj.get("name");
				long noOfShares = (long) obj.get("no_of_shares");
				double price = (double) obj.get("price");

				Stock stockObj = new Stock(name, price, noOfShares);
				list.add(stockObj);
			}
			getValue(list);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private static void getValue(List<Stock> list) {
		for (Stock stock : list) {
			double totalValue = stock.noOfShare * stock.price;
			stock.setTotalValue(totalValue);
			System.out.println(stock);
		}
	}
}
