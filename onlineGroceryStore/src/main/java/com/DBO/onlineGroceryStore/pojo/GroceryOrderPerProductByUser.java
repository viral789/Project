package com.DBO.onlineGroceryStore.pojo;

public class GroceryOrderPerProductByUser {

	private int orderId;
	private String productId;
	private int quantity;
	private double totalPriceForEachProduct;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPriceForEachProduct() {
		return totalPriceForEachProduct;
	}
	public void setTotalPriceForEachProduct(double totalPriceForEachProduct) {
		this.totalPriceForEachProduct = totalPriceForEachProduct;
	}
	@Override
	public String toString() {
		return "GroceryOrderPerProductByUser [orderId=" + getOrderId() + ", productId=" + getProductId()+ ", quantity="
				+ getQuantity() + ", totalPriceForEachProduct=" + getTotalPriceForEachProduct()+ "]";
	}
	
	
}
