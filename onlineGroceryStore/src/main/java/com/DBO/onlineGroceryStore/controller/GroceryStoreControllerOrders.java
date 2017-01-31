package com.DBO.onlineGroceryStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DBO.onlineGroceryStore.pojo.GroceryOrder;
import com.DBO.onlineGroceryStore.service.GroceryStoreServiceOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
public class GroceryStoreControllerOrders {

	GroceryStoreServiceOrder groceryStoreServiceOrder = new GroceryStoreServiceOrder();
	
	@RequestMapping(value="/orders/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody GroceryOrder insertOrder(@RequestBody GroceryOrder order){
		GroceryOrder ordr = new GroceryOrder();
		int orderID = groceryStoreServiceOrder.getMaxOrderID();
		orderID++;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		Date orderedDate = calendar.getTime();
		String orderDate = format.format(orderedDate);
		order.setIssueDATE(orderedDate);
		order.setOrderID(orderID);
		groceryStoreServiceOrder.insertOrder(order);
		ordr = groceryStoreServiceOrder.getOrderByOrderId(orderID);
		return ordr;
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ArrayList<GroceryOrder> getAllOrders(){
		ArrayList<GroceryOrder> orders = groceryStoreServiceOrder.getAllOrders();
		return orders;
	}
	
	@RequestMapping(value="orders/getByUserId/{userID}", method = RequestMethod.GET)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody ArrayList<GroceryOrder> getOrderByUserId(@PathVariable int userID){
		//int userId = order.getUserID();
		ArrayList<GroceryOrder> arrayListGrcOrd = groceryStoreServiceOrder.getOrderByUserId(userID);
		return arrayListGrcOrd;
	}
	
	@RequestMapping(value="orders/deleteOrder", method = RequestMethod.GET)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody int deleteOrder(@RequestBody GroceryOrder order){
		int orderId = order.getOrderID();
		int result = groceryStoreServiceOrder.deleteOrder(orderId);
		return result;
	}
	
	
	@RequestMapping(value="orders/updateOrder", method = RequestMethod.GET)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody int updateOrder(@RequestBody GroceryOrder order){
		int result = groceryStoreServiceOrder.updateOrder(order);
		return result;
	}

	
}

