package com.DBO.onlineGroceryStore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryWarehouse;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceWareHouse;

@Controller
public class GroceryStoreControllerWarehouse {

	GroceryStoreServiceWareHouse groceryStoreServiceWareHouse = new GroceryStoreServiceWareHouse();
	
	@RequestMapping(value="/allWarehouse", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<GroceryWarehouse> getAllWarehouse(){
		ArrayList<GroceryWarehouse> warehouses = groceryStoreServiceWareHouse.getAllWarehouse();
		return warehouses;
	}
}
