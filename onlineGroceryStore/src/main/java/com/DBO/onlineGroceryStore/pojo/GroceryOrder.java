package com.DBO.onlineGroceryStore.pojo;

import java.util.Date;

public class GroceryOrder {

	private int orderID;
	private long creditCARDNUMBER;
	private double totalPRICE;
	private Date issueDATE;
	private int status;
	private int userID;
	private double remBALANCE;
	
	public double getRemBALANCE() {
		return remBALANCE;
	}
	public void setRemBALANCE(double remBALANCE) {
		this.remBALANCE = remBALANCE;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public long getCreditCARDNUMBER() {
		return creditCARDNUMBER;
	}
	public void setCreditCARDNUMBER(long creditCARDNUMBER) {
		this.creditCARDNUMBER = creditCARDNUMBER;
	}
	public double getTotalPRICE() {
		return totalPRICE;
	}
	public void setTotalPRICE(double totalPRICE) {
		this.totalPRICE = totalPRICE;
	}
	public Date getIssueDATE() {
		return issueDATE;
	}
	public void setIssueDATE(Date issueDATE) {
		this.issueDATE = issueDATE;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		return "GroceryOrder [orderID=" + getOrderID() + ", creditCARDNUMBER=" + getCreditCARDNUMBER() + ", totalPRICE="
				+ getTotalPRICE() + ", issueDATE=" + getIssueDATE() + ", status=" + getStatus() + ", userID=" + getUserID()
				+ ", remBALANCE=" + getRemBALANCE() + "]";
	}
	
	
	
	
	
}
