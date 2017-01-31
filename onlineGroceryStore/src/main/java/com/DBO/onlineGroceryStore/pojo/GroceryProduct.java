package com.DBO.onlineGroceryStore.pojo;

public class GroceryProduct {

	private String productId;
	private int warehouseId;
	private String size;
	private String productType;
	private String additionalInformationContent;
	private String productName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAdditionalInformationContent() {
		return additionalInformationContent;
	}
	public void setAdditionalInformationContent(String additionalInformationContent) {
		this.additionalInformationContent = additionalInformationContent;
	}
	
	@Override
	public String toString() {
		return "GroceryProduct [productId=" + getProductId() + ", warehouseId=" + getWarehouseId() + ", size=" + getSize()
				+ ", productType=" + getProductType() + ", additionalInformationContent=" + getAdditionalInformationContent()
				+ "productName=" +getProductName()+"]";
	}
}
