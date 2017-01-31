package com.DBO.onlineGroceryStore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryProduct;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceProduct;

@Controller
public class GroceryStoreControllerProduct {

	GroceryStoreServiceProduct groceryStoreServiceProduct = new GroceryStoreServiceProduct();
	
	@RequestMapping(value="/product/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody List<GroceryProduct> addProduct(@RequestBody GroceryProduct groceryProduct){
		List<GroceryProduct> products = new ArrayList<GroceryProduct>();
		groceryStoreServiceProduct.addProduct(groceryProduct);
		products = groceryStoreServiceProduct.getAllProduct();
		return products;
	}
	
	@RequestMapping(value="/products", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryProduct> getAllProducts (){
		List<GroceryProduct> products = new ArrayList<GroceryProduct>();
		products = groceryStoreServiceProduct.getAllProduct();
		return products;
	}
	
	@RequestMapping(value="/products/{type}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryProduct> getAllProductsByType (@PathVariable String type){
		List<GroceryProduct> products = new ArrayList<GroceryProduct>();
		products = groceryStoreServiceProduct.getProductByType(type);
		return products;
	}
}
