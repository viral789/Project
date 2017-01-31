package com.DBO.onlineGroceryStore.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryStaff;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceStaff;

@Controller
public class GroceryStoreControllerStaff {

	GroceryStoreServiceStaff groceryStoreServiceStaff = new GroceryStoreServiceStaff();
	
	@RequestMapping(value="/staff/insert", method=RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody int addStaff (@RequestBody GroceryStaff groceryStaff){
		int result = groceryStoreServiceStaff.addStaff(groceryStaff);
		return result;
	}
}
