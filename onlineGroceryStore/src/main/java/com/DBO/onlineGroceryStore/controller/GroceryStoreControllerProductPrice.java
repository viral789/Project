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

import com.DBO.onlineGroceryStore.pojo.GroceryProductPrice;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceProductPrice;

@Controller
public class GroceryStoreControllerProductPrice {

GroceryStoreServiceProductPrice groceryStoreServiceProductPrice = new GroceryStoreServiceProductPrice();
	
	@RequestMapping(value="/price/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int addProduct(@RequestBody GroceryProductPrice groceryProductPrice){
		int result = groceryStoreServiceProductPrice.addProductPrice(groceryProductPrice);
		return result;
	}
	
	@RequestMapping(value="/price", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryProductPrice> getAllProducts (){
		List<GroceryProductPrice> products = new ArrayList<GroceryProductPrice>();
		products = groceryStoreServiceProductPrice.getallProductPrice();
		return products;
	}
	
	@RequestMapping(value="/priceById/{p_id}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody double getAllProductPriceById (@PathVariable String p_id){
		double price = groceryStoreServiceProductPrice.getProductPriceById(p_id);
		return price;
	}
}
