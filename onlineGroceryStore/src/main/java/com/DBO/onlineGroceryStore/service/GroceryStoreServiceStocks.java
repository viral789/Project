package com.DBO.onlineGroceryStore.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryStocks;
import com.DBO.onlineGroceryStore.pojo.GroceryUser;

@Controller
public class GroceryStoreServiceStocks {
		private Connection connection;

		public GroceryStoreServiceStocks(){
			connection = DBUtility.getConnection();
		}
		
		public GroceryStocks getStocks(String productId){
			
			GroceryStocks groceryStocks = new GroceryStocks();
			try{
				String query = "SELECT WAREHOUSEID, PRODUCTID, NOOFITEMS FROM STOCKS WHERE PRODUCTID= ?";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, productId);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs != null){
					while(rs.next()){
						groceryStocks.setWareHOUSEID(rs.getInt("WAREHOUSEID"));
						groceryStocks.setNoOFITEM(rs.getInt("NOOFITEMS"));
						groceryStocks.setProductID(rs.getString("PRODUCTID"));
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return groceryStocks;
		}

		
		public int addStocks(GroceryStocks stocks){
			int result =0;
			try{
				
				
				String query = "INSERT INTO STOCKS (WAREHOUSEID, PRODUCTID, NOOFITEMS) VALUES (?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, stocks.getWareHOUSEID());
				preparedStatement.setString(2, stocks.getProductID());
				preparedStatement.setDouble(3, stocks.getNoOFITEM());         
				
				result = preparedStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
		public int updateStocks(ArrayList<GroceryStocks> stocks){
			int result = 0;
			try{
				for(GroceryStocks loop: stocks){
					String query = "UPDATE STOCKS SET NOOFITEMS=? WHERE WAREHOUSEID=? AND PRODUCTID=?";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, loop.getNoOFITEM());
					preparedStatement.setInt(2, loop.getWareHOUSEID());
					preparedStatement.setString(3, loop.getProductID());
					result = preparedStatement.executeUpdate();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
		public List<GroceryStocks> getAllStocks(){
			List<GroceryStocks> groceryStocks = new ArrayList<GroceryStocks>();
			try{
				String query = "SELECT WAREHOUSEID, PRODUCTID, NOOFITEMS FROM STOCKS";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs != null){
					while(rs.next()){
						GroceryStocks groceryStock = new GroceryStocks();
						groceryStock.setWareHOUSEID(rs.getInt("WAREHOUSEID"));
						groceryStock.setNoOFITEM(rs.getInt("NOOFITEMS"));
						groceryStock.setProductID(rs.getString("PRODUCTID"));
						groceryStocks.add(groceryStock);
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return groceryStocks;
		}
	
}
