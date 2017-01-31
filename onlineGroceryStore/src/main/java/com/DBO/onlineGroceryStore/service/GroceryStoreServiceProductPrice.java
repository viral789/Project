package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryProductPrice;

public class GroceryStoreServiceProductPrice {

	private Connection connection;
	
	public GroceryStoreServiceProductPrice(){
		connection = DBUtility.getConnection();
	}
	
	public int addProductPrice(GroceryProductPrice groceryProductPrice){
		int result =0;
		try{
			String query = "INSERT INTO PRODUCTPRICE (PRODUCTID, ZIPCODE, PRICE) values"
					+ "(?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, groceryProductPrice.getProductId());
			preparedStatement.setInt(2, groceryProductPrice.getZipcode());
			preparedStatement.setDouble(3, groceryProductPrice.getPrice());
			result = preparedStatement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public double getProductPriceById (String productId){
		double result = 0;
		try{
			String query = "SELECT * FROM PRODUCTPRICE WHERE PRODUCTID = " + productId;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs !=null){
				while(rs.next()){
					GroceryProductPrice groceryProductPrice = new GroceryProductPrice();
					groceryProductPrice.setPrice(rs.getDouble("PRICE"));
					result = groceryProductPrice.getPrice();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<GroceryProductPrice> getallProductPrice(){
		
		List<GroceryProductPrice> productPrices = new ArrayList<GroceryProductPrice>();
		try{
			String query = "SELECT * FROM PRODUCTPRICE ORDER BY PRODUCTID DESC" ;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs !=null){
				while(rs.next()){
					GroceryProductPrice groceryProductPrice = new GroceryProductPrice();
					groceryProductPrice.setPrice(rs.getDouble("PRICE"));
					groceryProductPrice.setProductId(rs.getString("PRODUCTID"));
					groceryProductPrice.setZipcode(rs.getInt("ZIPCODE"));
					
					productPrices.add(groceryProductPrice);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return productPrices;
	}
}
