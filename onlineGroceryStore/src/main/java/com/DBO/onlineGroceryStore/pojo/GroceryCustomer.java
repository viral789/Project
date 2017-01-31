package com.DBO.onlineGroceryStore.pojo;

public class GroceryCustomer {

	private int userId;
	private long creditCardNo;	
	private int expMONTH;
	private int expYEAR;
	
	public int getExpMONTH() {
		return expMONTH;
	}
	public void setExpMONTH(int expMONTH) {
		this.expMONTH = expMONTH;
	}
	public int getExpYEAR() {
		return expYEAR;
	}
	public void setExpYEAR(int expYEAR) {
		this.expYEAR = expYEAR;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(long creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	@Override
	public String toString() {
		return "GroceryCustomer [userId=" + getUserId() + ", creditCardNo=" + getCreditCardNo() + ", expMONTH=" + getExpMONTH()
				+ ", expYEAR=" + getExpYEAR() + "]";
	}

}
