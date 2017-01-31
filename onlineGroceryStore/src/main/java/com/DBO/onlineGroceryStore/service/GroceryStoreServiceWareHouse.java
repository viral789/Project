package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryWarehouse;

public class GroceryStoreServiceWareHouse {

	private Connection connection;
	
	public GroceryStoreServiceWareHouse(){
		connection = DBUtility.getConnection();
	}
	
	public ArrayList<GroceryWarehouse> getAllWarehouse(){
		ArrayList<GroceryWarehouse> groceryWarehouses = new ArrayList<GroceryWarehouse>();
		try{
			String query = "SELECT * FROM WAREHOUSE";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					GroceryWarehouse groceryWarehouse = new GroceryWarehouse();
					groceryWarehouse.setWarehouseId(rs.getInt("WAREHOUSEID"));
					groceryWarehouse.setCapacity(rs.getInt("CAPACITY"));
					groceryWarehouse.setZipcode(rs.getInt("ZIPCODE"));
					groceryWarehouses.add(groceryWarehouse);
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return groceryWarehouses;
	}
}
