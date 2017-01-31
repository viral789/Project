package com.DBO.onlineGroceryStore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBO.onlineGroceryStore.model.DBUtility;
import com.DBO.onlineGroceryStore.pojo.GroceryProduct;

import oracle.jdbc.proxy.annotation.Pre;

public class GroceryStoreServiceProduct {

	private Connection connection;
	
	public GroceryStoreServiceProduct(){
		connection  = DBUtility.getConnection();
	}
	
	public int addProduct(GroceryProduct product){
		int result = 0;
		try{
			String query = "INSERT INTO PRODUCT (PRODUCTID, WAREHOUSEID, SIZES, PRODUCTTYPE, ADDITIONALINFORMATIONCONTENT, PRODUCTNAME) VALUES"
					+ "(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, product.getProductId());
			preparedStatement.setInt(2, product.getWarehouseId());
			preparedStatement.setString(3, product.getSize());
			preparedStatement.setString(4, product.getProductType());
			preparedStatement.setString(5, product.getAdditionalInformationContent());
			preparedStatement.setString(6,  product.getProductName());
			
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<GroceryProduct> getProductByType(String productType){
		List<GroceryProduct> product = new ArrayList<GroceryProduct>();
		try{
			String query = "SELECT * FROM PRODUCT WHERE PRODUCTTYPE = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, productType);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs != null){
				while(rs.next()){
					GroceryProduct groceryProduct = new GroceryProduct();
					groceryProduct.setProductId(rs.getString("PRODUCTID"));
					groceryProduct.setWarehouseId(rs.getInt("WAREHOUSEID"));
					groceryProduct.setSize(rs.getString("SIZES"));
					groceryProduct.setProductType(rs.getString("PRODUCTTYPE"));
					groceryProduct.setAdditionalInformationContent(rs.getString("ADDITIONALINFORMATIONCONTENT"));
					groceryProduct.setProductName(rs.getString("PRODUCTNAME"));
					product.add(groceryProduct);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return product;
	}
	
	public List<GroceryProduct> getAllProduct (){
		List<GroceryProduct> products = new ArrayList<GroceryProduct>();
		try{
			String query = "SELECT * FROM PRODUCT";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs !=null){
				while(rs.next()){
					GroceryProduct groceryProduct = new GroceryProduct();
					groceryProduct.setProductId(rs.getString("PRODUCTID"));
					groceryProduct.setWarehouseId(rs.getInt("WAREHOUSEID"));
					groceryProduct.setSize(rs.getString("SIZES"));
					groceryProduct.setProductType(rs.getString("PRODUCTTYPE"));
					groceryProduct.setAdditionalInformationContent(rs.getString("ADDITIONALINFORMATIONCONTENT"));
					groceryProduct.setProductName(rs.getString("PRODUCTNAME"));
					products.add(groceryProduct);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return products;
	}
}
