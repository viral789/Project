package com.DBO.onlineGroceryStore.pojo;

public class GroceryProductPrice {
	
	private String productId;
	private int zipcode;
	private double price;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "GroceryProductPrice [productId=" + getProductId() + ", zipcode=" + getZipcode() + ", price=" + getPrice() + "]";
	}
	
	
}
