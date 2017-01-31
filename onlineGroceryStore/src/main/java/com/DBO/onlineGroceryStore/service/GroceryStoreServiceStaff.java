package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryStaff;

public class GroceryStoreServiceStaff {

	private Connection connection;
	
	public GroceryStoreServiceStaff(){
		connection =  DBUtility.getConnection();
	}
	
	public int addStaff(GroceryStaff groceryStaff){
		int result =0;
		try{
			String query = "INSERT INTO STAFF(USERID, JOBTITLE, SALARY) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, groceryStaff.getUserId());
			preparedStatement.setString(2, groceryStaff.getJobTitle());
			preparedStatement.setInt(3, groceryStaff.getSalary());
			
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public GroceryStaff getStaffByID(int userID){
		GroceryStaff groceryStaff = new GroceryStaff();
		try{
			String query = "SELECT * FROM STAFF WHERE USERID =?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					groceryStaff.setUserId(rs.getInt("USERID"));
					groceryStaff.setJobTitle(rs.getString("JOBTITLE"));
					groceryStaff.setSalary(rs.getInt("SALARY"));
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return groceryStaff;
	}
}
