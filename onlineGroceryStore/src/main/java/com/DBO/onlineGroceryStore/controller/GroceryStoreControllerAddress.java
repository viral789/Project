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

import com.DBO.onlineGroceryStore.pojo.GroceryAddress;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceAddress;

@Controller
public class GroceryStoreControllerAddress {
	
	
	GroceryStoreServiceAddress groceryStoreServiceAddress = new GroceryStoreServiceAddress();
	
	@RequestMapping(value="/address/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int addAddress (@RequestBody GroceryAddress address){
		int result = 0;
		int addrID = groceryStoreServiceAddress.getMaxAddrID();
		addrID++;
		address.setAddrID(addrID);
		result = groceryStoreServiceAddress.addAddress(address);
		return result;
	}
	
	@RequestMapping(value="/address/byAddrID/{addrId}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody GroceryAddress getAddressByAddrId(@PathVariable int addrId){
		GroceryAddress address = groceryStoreServiceAddress.getAddressByAddrId(addrId);
		return address;
	}
	
	@RequestMapping(value="/address/byUserID/{userId}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody GroceryAddress getAddressByUserId(@PathVariable int userId){
		GroceryAddress address = groceryStoreServiceAddress.getAddressByUserId(userId);
		return address;
	}


	@RequestMapping(value="/address/byCreditCard/{creditCardNo}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody GroceryAddress getAddressByCreditCardNo(@PathVariable long creditCardNo){
		GroceryAddress address = groceryStoreServiceAddress.getAddressByCreditCardNo(creditCardNo);
		return address;
	}

	@RequestMapping(value="/address/update", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int updateAddress (@RequestBody GroceryAddress address){
		int result = 0;
		result = groceryStoreServiceAddress.updateAddress(address);
		return result;
	}
	
	@RequestMapping(value="/address/delete/{userID}", method=RequestMethod.DELETE)
	public @ResponseBody int deleteAddress (@PathVariable int userID){
		int result = groceryStoreServiceAddress.deleteAddress(userID);
		return result;
	}
}
