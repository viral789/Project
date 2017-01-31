package com.DBO.onlineGroceryStore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.DBO.onlineGroceryStore.pojo.GroceryStocks;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceStocks;

@Controller
public class GroceryStoreControllerStocks {

	GroceryStoreServiceStocks groceryStoreServiceStocks = new GroceryStoreServiceStocks();
	
	@RequestMapping(value="/stocks/insert", method=RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int addStocks(@RequestBody GroceryStocks stocks){
		int result = groceryStoreServiceStocks.addStocks(stocks);
		return result;
	}
	
	@RequestMapping(value="/stocks/{product_id}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryStocks> getStocksById (@PathVariable String product_id){
		List<GroceryStocks> groceryStocks = new ArrayList<GroceryStocks>();
		GroceryStocks groceryStock = new GroceryStocks();
		groceryStock = groceryStoreServiceStocks.getStocks(product_id);
		groceryStocks.add(groceryStock);
		return groceryStocks;
	}

	@RequestMapping(value="/stocks/update", method=RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int updateStocks(@RequestBody ArrayList<GroceryStocks> stocks){
		int result = groceryStoreServiceStocks.updateStocks(stocks);
		return result;		
	}
	
	@RequestMapping(value="/allStocks", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryStocks> getAllStocks (){
		List<GroceryStocks> groceryStocks = new ArrayList<GroceryStocks>();
		groceryStocks = groceryStoreServiceStocks.getAllStocks();
		return groceryStocks;
	}
	
}
