package com.DBO.onlineGroceryStore.service;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroceryStoreServiceUser {

	private Connection connection;
	
	public GroceryStoreServiceUser(){
		connection =  DBUtility.getConnection();
	}
	
	public int addUser(GroceryUser groceryUser)
	{
		int resultSet = 0;	
		try {
			String query = "INSERT INTO USERS (USERID, USERNAME, PASSWORD, TYPE, FNAME, LNAME, EMAILID) VALUES(?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
		  	preparedStatement.setInt(1, groceryUser.getUserId());
		  	preparedStatement.setString(2, groceryUser.getUserName());
		  	preparedStatement.setString(3, groceryUser.getPassWord());
		  	preparedStatement.setInt(4, groceryUser.getType());
		  	preparedStatement.setString(5, groceryUser.getFirstName());
		  	preparedStatement.setString(6, groceryUser.getLastName());
		  	preparedStatement.setString(7, groceryUser.geteMailId());
		  	
		  	resultSet = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public int getLatestUserID(){
		int result = 0;
		try{
			String query = "select max(USERID) from users";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					result = rs.getInt("MAX(USERID)");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public GroceryUser getUserByUserID(int userId)
	{
		GroceryUser groceryUser = new GroceryUser();
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM USERS WHERE USERID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{				
					groceryUser.setUserId(resultSet.getInt("USERID"));
					groceryUser.setUserName(resultSet.getString("USERNAME"));
					groceryUser.setPassWord(resultSet.getString("PASSWORD"));
					groceryUser.setType(resultSet.getInt("TYPE"));
					groceryUser.setFirstName(resultSet.getString("FNAME"));
					groceryUser.setLastName(resultSet.getString("LNAME"));
					groceryUser.seteMailId(resultSet.getString("EMAILID"));						
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryUser;	
	}
	
	public GroceryUser getUserByUserName (String username, String password){
		//List<GroceryUser> users = new ArrayList<GroceryUser>();
		GroceryUser groceryUser = new GroceryUser();
		try{
			String query = "select * from users where username=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next()){
					//GroceryUser groceryUser = new GroceryUser();
					groceryUser.setUserId(resultSet.getInt("USERID"));
					groceryUser.setUserName(resultSet.getString("USERNAME"));
					groceryUser.setPassWord(resultSet.getString("PASSWORD"));
					groceryUser.setType(resultSet.getInt("TYPE"));
					groceryUser.setFirstName(resultSet.getString("FNAME"));
					groceryUser.setLastName(resultSet.getString("LNAME"));
					groceryUser.seteMailId(resultSet.getString("EMAILID"));
					//users.add(groceryUser);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryUser;
	}
	
	public List<GroceryUser> getAllUsers()
	{
		List<GroceryUser> arrayList = new ArrayList<GroceryUser>();
		try {
			String query = "SELECT * FROM USERS";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next()){
					GroceryUser groceryUser = new GroceryUser();
					groceryUser.setUserId(resultSet.getInt("USERID"));
					groceryUser.setUserName(resultSet.getString("USERNAME"));
					groceryUser.setPassWord(resultSet.getString("PASSWORD"));
					groceryUser.setType(resultSet.getInt("TYPE"));
					groceryUser.setFirstName(resultSet.getString("FNAME"));
					groceryUser.setLastName(resultSet.getString("LNAME"));
					groceryUser.seteMailId(resultSet.getString("EMAILID"));	
					arrayList.add(groceryUser);	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;	
		
	}
	
	public int updateUser(GroceryUser groceryUser)
	{
		int result= 0;	
		
		try {
			String query = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, TYPE = ? , FNAME = ?, LNAME=?, EMAILID = ? WHERE USERID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, groceryUser.getUserName());
			preparedStatement.setString(2, groceryUser.getPassWord());
			preparedStatement.setInt(3, groceryUser.getType());
			preparedStatement.setString(4, groceryUser.getFirstName());
			preparedStatement.setString(5, groceryUser.getLastName());
			preparedStatement.setString(6, groceryUser.geteMailId());
						
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {		
			e.printStackTrace();
		}	
		return result;
	}
	
	public int deleteUser(int userId)
	{
			int result = 0;	
			
			try{
				String query = "DELETE FROM USERS WHERE USERID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);			
				preparedStatement.setInt(1, userId);
				result = preparedStatement.executeUpdate();
			}
			catch(SQLException e){
			e.printStackTrace();
		}

			return result;
	}
}