package com.DBO.onlineGroceryStore.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryCustomer;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceCustomer;

@Controller
public class GroceryStoreControllerCustomer {

	GroceryStoreServiceCustomer groceryStoreServiceCustomer =  new GroceryStoreServiceCustomer();
	
	@RequestMapping(value="/customer/insert", method=RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int addCustomer(@RequestBody GroceryCustomer customer){
		int result = groceryStoreServiceCustomer.addCustomer(customer);
		return result;
	}
	
	@RequestMapping(value="/customers/{userId}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody GroceryCustomer getCustomerById (@PathVariable int userId){
		GroceryCustomer user = new GroceryCustomer();
		user = groceryStoreServiceCustomer.getCustomerByID(userId);
		return user;
	}
	
	@RequestMapping(value="/customer/update", method=RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int updateCreditCard(@RequestBody GroceryCustomer groceryCustomer){
		
		int result = groceryStoreServiceCustomer.updateCredCard(groceryCustomer);
		return result;
	}
}
