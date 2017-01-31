package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryOrder;
import com.DBO.onlineGroceryStore.pojo.GroceryOrderPerProductByUser;

public class GroceryStoreServiceOrderPerProductByUser {

	private Connection connection;
	
	public GroceryStoreServiceOrderPerProductByUser(){
		connection = DBUtility.getConnection();
	}
	
	public int addProductByUser(ArrayList<GroceryOrderPerProductByUser> byUser){
		int result = 0;
		try{
			
			for(GroceryOrderPerProductByUser forLoop : byUser)
			{
			String query = "INSERT INTO  ORDERS_FOR_EACH_USER (ORDERID,PRODUCTID,QUANTITY,TOTALPRICEPERPRODUCT) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, forLoop.getOrderId());
			preparedStatement.setString(2, forLoop.getProductId());
			preparedStatement.setInt(3, forLoop.getQuantity());
			preparedStatement.setDouble(4, forLoop.getTotalPriceForEachProduct());
			
			result = preparedStatement.executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<GroceryOrderPerProductByUser> getProductByUser (){
		List<GroceryOrderPerProductByUser> byUsers = new ArrayList<GroceryOrderPerProductByUser>();
		try{
			String query = "SELECT * FROM orders_for_each_user";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs!=null){
				while(rs.next()){
					GroceryOrderPerProductByUser byUser = new GroceryOrderPerProductByUser();
					byUser.setOrderId(rs.getInt("ORDERID"));
					byUser.setProductId(rs.getString("PRODUCTID"));
					byUser.setQuantity(rs.getInt("Quantity"));
					byUser.setTotalPriceForEachProduct(rs.getDouble("totalPriceForEachProduct"));
					
					byUsers.add(byUser);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return byUsers;
	}
}
