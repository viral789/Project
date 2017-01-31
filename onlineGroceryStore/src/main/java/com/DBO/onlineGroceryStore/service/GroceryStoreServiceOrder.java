package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryOrder;

public class GroceryStoreServiceOrder {
	
	private Connection connection;

	public GroceryStoreServiceOrder(){
		connection = DBUtility.getConnection();
	}
	
	public int getMaxOrderID(){
		int result = 0;
		try{
			String query = "SELECT MAX(ORDERID) FROM ORDERS";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					result = rs.getInt("MAX(ORDERID)");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public int insertOrder(GroceryOrder order){
		int result =0;
		try{
			
			//converting java.util.Date to java.sql.Date starts here 
			
			java.sql.Date sqlIssueDate = new java.sql.Date(order.getIssueDATE().getTime());
			
			//converting java.util.Date to java.sql.Date ends here
			
			//String query = "INSERT INTO ORDERS (ORDERID, QUANTITY, CREDITCARDNO, TOTALPRICE, ISSUEDATE, STATUS, USERID, PRODUCTID) VALUES (?,?,?,?,?,?,?,?)";
			String query = "INSERT INTO ORDERS (ORDERID, CREDITCARDNO, TOTALPRICE, ISSUEDATE, STATUS, USERID, REM_BALANCE) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, order.getOrderID()); 
			preparedStatement.setLong(2, order.getCreditCARDNUMBER());
			preparedStatement.setDouble(3, order.getTotalPRICE());
			preparedStatement.setDate(4, sqlIssueDate);
			preparedStatement.setInt(5, order.getStatus());
			preparedStatement.setInt(6, order.getUserID());
			preparedStatement.setDouble(7, order.getRemBALANCE());
			
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	
	//Get All Orders starts here
	
	public ArrayList<GroceryOrder> getAllOrders()
	{
		ArrayList<GroceryOrder> groceryOrderList = new ArrayList<GroceryOrder>();
		
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM ORDERS";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{	
					GroceryOrder groceryOrder = new GroceryOrder();
					groceryOrder.setOrderID(resultSet.getInt("ORDERID"));
					//groceryOrder.setQuantity(resultSet.getInt("QUANTITY"));
					groceryOrder.setCreditCARDNUMBER(resultSet.getLong("CREDITCARDNO"));
					groceryOrder.setTotalPRICE(resultSet.getDouble("TOTALPRICE"));
					groceryOrder.setIssueDATE(resultSet.getDate("ISSUEDATE"));
					groceryOrder.setStatus(resultSet.getInt("STATUS"));
					groceryOrder.setUserID(resultSet.getInt("USERID"));
					groceryOrder.setRemBALANCE(resultSet.getDouble("REM_BALANCE"));
					
					groceryOrderList.add(groceryOrder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryOrderList;	
	}


	//Get All Orders ends here
	
	
	
	//Get Order by int orderId starts here 
	
	public GroceryOrder getOrderByOrderId(int orderId)
	{
		GroceryOrder groceryOrder = new GroceryOrder();
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM ORDERS WHERE ORDERID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{				
					groceryOrder.setOrderID(resultSet.getInt("ORDERID"));
					//groceryOrder.setQuantity(resultSet.getInt("QUANTITY"));
					groceryOrder.setCreditCARDNUMBER(resultSet.getLong("CREDITCARDNO"));
					groceryOrder.setTotalPRICE(resultSet.getDouble("TOTALPRICE"));
					groceryOrder.setIssueDATE(resultSet.getDate("ISSUEDATE"));
					groceryOrder.setStatus(resultSet.getInt("STATUS"));
					groceryOrder.setUserID(resultSet.getInt("USERID"));
					groceryOrder.setRemBALANCE(resultSet.getDouble("REM_BALANCE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryOrder;	
	}

	//Get Order by int orderId ends here 
	
	
	//Get All Orders by user starts here
	
	public ArrayList<GroceryOrder> getOrderByUserId(int userId)
	{
		ArrayList<GroceryOrder> groceryOrderList = new ArrayList<GroceryOrder>();		
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM ORDERS WHERE USERID = ? ORDER BY ORDERID ASC";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next())
				{					
					GroceryOrder groceryOrder = new GroceryOrder();
					groceryOrder.setOrderID(resultSet.getInt("ORDERID"));
					groceryOrder.setCreditCARDNUMBER(resultSet.getLong("CREDITCARDNO"));
					groceryOrder.setTotalPRICE(resultSet.getDouble("TOTALPRICE"));
					groceryOrder.setIssueDATE(resultSet.getDate("ISSUEDATE"));
					groceryOrder.setStatus(resultSet.getInt("STATUS"));
					groceryOrder.setUserID(resultSet.getInt("USERID"));
					groceryOrder.setRemBALANCE(resultSet.getDouble("REM_BALANCE"));
					groceryOrderList.add(groceryOrder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groceryOrderList;	
	}
	
	public int updateOrder(GroceryOrder order) 
	{
		int result = 0;
		//converting java.util.Date to java.sql.Date starts here 
		
		java.sql.Date sqlIssueDate = new java.sql.Date(order.getIssueDATE().getTime());
		
		//converting java.util.Date to java.sql.Date ends here

		
		String query = "UPDATE ORDERS TOTALPRICE = ?, ISSUEDATE = ?, STATUS= ? WHERE ORDERID =?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, order.getTotalPRICE());
			preparedStatement.setDate(2, sqlIssueDate);
			preparedStatement.setInt(3, order.getStatus());
			preparedStatement.setInt(4, order.getOrderID());
			
			result = preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteOrder(int orderId)
	{
		int result = 0;
		
		String query ="DELETE FROM ORDERS WHERE ORDERID = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderId);
			
			result = preparedStatement.executeUpdate();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		
		
		return result;		
		
	}
}
