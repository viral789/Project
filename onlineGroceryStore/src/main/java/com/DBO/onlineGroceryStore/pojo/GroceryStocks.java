package com.DBO.onlineGroceryStore.pojo;

public class GroceryStocks {

	int wareHOUSEID;
	String productID;
	int noOFITEM;
	public int getWareHOUSEID() {
		return wareHOUSEID;
	}
	public void setWareHOUSEID(int wareHOUSEID) {
		this.wareHOUSEID = wareHOUSEID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getNoOFITEM() {
		return noOFITEM;
	}
	public void setNoOFITEM(int noOFITEM) {
		this.noOFITEM = noOFITEM;
	}
	@Override
	public String toString() {
		return "GroceryStocks [wareHOUSEID=" + getWareHOUSEID() + ", productID=" + getProductID() + ", noOFITEM=" + getNoOFITEM()
				+ "]";
	}
		
	
}
