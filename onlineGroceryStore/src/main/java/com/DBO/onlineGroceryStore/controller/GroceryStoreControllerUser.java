package com.DBO.onlineGroceryStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryUser;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceUser;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
public class GroceryStoreControllerUser {

	GroceryStoreServiceUser groceryStoreServiceUser = new GroceryStoreServiceUser();
	
	@RequestMapping(value="/")
	public String Main(){
		return "index";
	}
	
	@RequestMapping(value="/users/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody GroceryUser addUser(@RequestBody GroceryUser user){
		GroceryUser usr = new GroceryUser();
		//List<GroceryUser> groceryUser = new ArrayList<GroceryUser>();
		int userID = groceryStoreServiceUser.getLatestUserID();
		userID++;
		user.setUserId(userID);
		groceryStoreServiceUser.addUser(user);
		usr = groceryStoreServiceUser.getUserByUserID(userID);
		return usr;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryUser> getAllUsers(){
		List<GroceryUser> users = groceryStoreServiceUser.getAllUsers();
		return users;
	}
	
	@RequestMapping(value="users/login", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody GroceryUser authenticate(@RequestBody GroceryUser user){
		String username = user.getUserName();
		String password = user.getPassWord();
		GroceryUser users= groceryStoreServiceUser.getUserByUserName(username, password);
		return users;
	}
}
