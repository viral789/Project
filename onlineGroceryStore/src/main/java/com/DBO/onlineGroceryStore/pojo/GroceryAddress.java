package com.DBO.onlineGroceryStore.pojo;

public class GroceryAddress {

	int addrID;
	int userID;		
	String streetNO;	
	int zipCODE;		
	int type;		
	long creditCARDNO;
	
	public int getAddrID() {
		return addrID;
	}
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getStreetNO() {
		return streetNO;
	}
	public void setStreetNO(String streetNO) {
		this.streetNO = streetNO;
	}
	public int getZipCODE() {
		return zipCODE;
	}
	public void setZipCODE(int l) {
		this.zipCODE = l;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getCreditCARDNO() {
		return creditCARDNO;
	}
	public void setCreditCARDNO(long creditCARDNO) {
		this.creditCARDNO = creditCARDNO;
	}
	@Override
	public String toString() {
		return "GroceryAddress [addrID=" + addrID + ", userID=" + userID + ", streetNO=" + streetNO + ", zipCODE="
				+ zipCODE + ", type=" + type + ", creditCARDNO=" + creditCARDNO + "]";
	}
}
