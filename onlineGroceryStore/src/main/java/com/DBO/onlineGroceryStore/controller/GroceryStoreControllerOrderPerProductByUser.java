package com.DBO.onlineGroceryStore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryOrder;
import com.DBO.onlineGroceryStore.pojo.GroceryOrderPerProductByUser;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceOrder;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceOrderPerProductByUser;

@Controller
public class GroceryStoreControllerOrderPerProductByUser {

GroceryStoreServiceOrderPerProductByUser groceryStoreServiceOrderPerProductByUser = new GroceryStoreServiceOrderPerProductByUser();
	
	

@RequestMapping(value="/ordersPerProduct/insert", method = RequestMethod.POST)
@Consumes(MediaType.APPLICATION_JSON)
public @ResponseBody int addOrderPerProduct (@RequestBody ArrayList<GroceryOrderPerProductByUser> byUser){
	int result =0;
	GroceryStoreServiceOrder groceryStoreServiceOrder = new GroceryStoreServiceOrder();
	int orderID = groceryStoreServiceOrder.getMaxOrderID();
	for(int i =0; i < byUser.size(); i++){
		byUser.get(i).setOrderId(orderID);
	}
	result = groceryStoreServiceOrderPerProductByUser.addProductByUser(byUser);
	return result;
}

@RequestMapping(value="/ordersPerProduct", method = RequestMethod.GET, headers = "Accept=application/json")
public @ResponseBody List<GroceryOrderPerProductByUser> getAllOrdersPerProduct(){
	List<GroceryOrderPerProductByUser> orders = groceryStoreServiceOrderPerProductByUser.getProductByUser();
	return orders;
}


}
