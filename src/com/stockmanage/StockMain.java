package com.stockmanage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class StockMain {
	
	public static Scanner sc = new Scanner(System.in);
	public static JSONArray stockList = new JSONArray();
	public static void main(String[] args) {
		System.out.println("------- Welcome to Stock Management -------");
		getInputFromUser();
	}
	private static void getInputFromUser(){
		System.out.println("Which operation do you want to perform ?\n1.Add Stock \n2.Print stock report \n3.Exit");
		int ch = sc.nextInt();
		switch (ch){
		case 1:
			addStock();		//add stock
			break;
		case 2:
			printStock();	//print the stock
			break;
		case 3:
			System.out.println("Goodbye!");	//exit
			System.exit(1);
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}

	}

	/**
	 * add stock method
	 */
	public static void addStock() {
		System.out.println(" <- Add stock ->");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Stock Name : ");
		String stockName = sc.nextLine();
		System.out.println("Enter number of shares: ");
		int noOfShares = sc.nextInt();
		System.out.println("Enter share price: ");
		double sharePrice = sc.nextDouble();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name",stockName); 	// Assiging the accepted value to the JSON Object
		jsonObject.put("no_of_shares",noOfShares);
		jsonObject.put("price",sharePrice);
		stockList.add(jsonObject); 		// Adding the JSON Object into the JSON Array..
		try {
			FileWriter file = new FileWriter("/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StockManagement/data/stockdetails.json");
			file.write(stockList.toJSONString()); //write to file
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Added: "+jsonObject);
		getInputFromUser();
	}

	/**
	 * To print the stock and value
	 */
	public static void printStock() {
		System.out.println("<- Print stock details ->");
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("/Users/anirudhasm/Desktop/eclipse-yml_training_workspace/StockManagement/data/stockdetails.json"));
			for (int i =0; i < jsonArray.size();i++){
				System.out.printf("******** Stock %s ********\n",i+1);
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String name = (String) obj.get("name");
				long shares = (long) obj.get("no_of_shares");
				Double price = (Double) obj.get("price");
				System.out.println("Stock Name : " +name);
				System.out.println("Number of Shares : " +shares);
				System.out.println("Stock price : " +price);
				System.out.println("Value of stocks : "+ (shares*price));
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println("File Not Found");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("File IO Exception");
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		getInputFromUser();
	}

}

