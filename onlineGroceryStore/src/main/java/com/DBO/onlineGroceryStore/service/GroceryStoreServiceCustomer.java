package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryCustomer;

public class GroceryStoreServiceCustomer {

	private Connection connection;
	
	public GroceryStoreServiceCustomer(){
		connection = DBUtility.getConnection();
	}
	
	public int addCustomer(GroceryCustomer customer){
		int result =0;
		try{
			String query = "INSERT INTO CUSTOMER(USERID, CREDITCARDNO, EXP_MONTH, EXP_YEAR) VALUES (?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getUserId());
			preparedStatement.setLong(2, customer.getCreditCardNo());
			preparedStatement.setInt(3, customer.getExpMONTH());
			preparedStatement.setInt(4, customer.getExpYEAR());
			
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public GroceryCustomer getCustomerByID (int userID){
		GroceryCustomer cust = new GroceryCustomer();
		try{
			String query = "SELECT * FROM CUSTOMER WHERE USERID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					cust.setUserId(rs.getInt("USERID"));
					cust.setCreditCardNo(rs.getLong("CREDITCARDNO"));
					cust.setExpMONTH(rs.getInt("EXP_MONTH"));
					cust.setExpYEAR(rs.getInt("EXP_YEAR"));
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cust;
	}
	
	public int updateCredCard(GroceryCustomer groceryCustomer)
	{
		int result = 0;
		String query = "UPDATE CUSTOMER SET EXP_MONTH=? ,EXP_YEAR =? WHERE USERID = ?";
		
		try {
			PreparedStatement preparedStatement =  connection.prepareStatement(query);
			
			preparedStatement.setInt(1, groceryCustomer.getExpMONTH());
			preparedStatement.setInt(2, groceryCustomer.getExpYEAR());
			preparedStatement.setInt(3, groceryCustomer.getUserId());			
			result = preparedStatement.executeUpdate();			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
