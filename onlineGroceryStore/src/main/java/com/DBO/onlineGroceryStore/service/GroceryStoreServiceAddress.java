package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryAddress;

public class GroceryStoreServiceAddress {

	private Connection connection;

	public GroceryStoreServiceAddress(){
		connection = DBUtility.getConnection();
	}

	
	public int addAddress(GroceryAddress address){
		int result =0;
		try{
			String query = "INSERT INTO ADDRESS(ADDRID, USERID, STREETNO,ZIPCODE, TYPE, CRDEITCARDNO) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, address.getAddrID());
			preparedStatement.setInt(2, address.getUserID());
			preparedStatement.setString(3, address.getStreetNO());
			preparedStatement.setInt(4, address.getZipCODE());
			preparedStatement.setInt(5, address.getType());
			preparedStatement.setLong(6, address.getCreditCARDNO());
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getMaxAddrID(){
		int result = 0;
		try{
			String query = "SELECT MAX(ADDRID) FROM ADDRESS";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					result = rs.getInt("MAX(ADDRID)");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public GroceryAddress getAddressByUserId(int userId)
	{
		GroceryAddress groceryAdress = new GroceryAddress();
		@SuppressWarnings("resource")
		

		ResultSet resultSet = null;
		try {
			String query = "SELECT ADDRID, USERID, STREETNO,ZIPCODE, TYPE, CRDEITCARDNO FROM ADDRESS WHERE USERID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{				
					groceryAdress.setAddrID(resultSet.getInt("ADDRID"));
					groceryAdress.setUserID(resultSet.getInt("USERID"));
					groceryAdress.setStreetNO(resultSet.getString("STREETNO"));
					groceryAdress.setZipCODE(resultSet.getInt("ZIPCODE"));
					groceryAdress.setType(resultSet.getInt("TYPE"));
					groceryAdress.setCreditCARDNO(resultSet.getLong("CRDEITCARDNO"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryAdress;	
	}

	
	public GroceryAddress getAddressByCreditCardNo(long creditCardNo)
	{
		GroceryAddress groceryAdress = new GroceryAddress();
		@SuppressWarnings("resource")
		

		ResultSet resultSet = null;
		try {
			String query = "SELECT ADDRID, USERID, STREETNO,ZIPCODE, TYPE, CRDEITCARDNO FROM ADDRESS WHERE CRDEITCARDNO = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, creditCardNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{				
					groceryAdress.setAddrID(resultSet.getInt("ADDRID"));
					groceryAdress.setUserID(resultSet.getInt("USERID"));
					groceryAdress.setStreetNO(resultSet.getString("STREETNO"));
					groceryAdress.setZipCODE(resultSet.getInt("ZIPCODE"));
					groceryAdress.setType(resultSet.getInt("TYPE"));
					groceryAdress.setCreditCARDNO(resultSet.getLong("CRDEITCARDNO"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryAdress;	
	}


	public GroceryAddress getAddressByAddrId(int addrId)
	{
		GroceryAddress groceryAdress = new GroceryAddress();
		ResultSet resultSet = null;
		try {
			String query = "SELECT ADDRID, USERID, STREETNO,ZIPCODE, TYPE, CRDEITCARDNO FROM ADDRESS WHERE ADDRID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, addrId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{				
					groceryAdress.setAddrID(resultSet.getInt("ADDRID"));
					groceryAdress.setUserID(resultSet.getInt("USERID"));
					groceryAdress.setStreetNO(resultSet.getString("STREETNO"));
					groceryAdress.setZipCODE(resultSet.getInt("ZIPCODE"));
					groceryAdress.setType(resultSet.getInt("TYPE"));
					groceryAdress.setCreditCARDNO(resultSet.getLong("CRDEITCARDNO"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return groceryAdress;	
	}
	
	public int updateAddress(GroceryAddress address)
	{
		int result = 0;
		String query = "UPDATE ADDRESS SET STREETNO= ?, ZIPCODE =? WHERE USERID =?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, address.getStreetNO());
			preparedStatement.setInt(2, address.getZipCODE());
			preparedStatement.setInt(3, address.getUserID());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteAddress (int userID){
		int result = 0;
		try{
			String query = "DELETE FROM ADDRESS WHERE USERID = " + userID;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
}
