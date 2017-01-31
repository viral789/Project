package com.DBO.onlineGroceryStore.pojo;

public class GroceryWarehouse {
	private int warehouseId;
	private int capacity;
	private int zipcode;
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "GroceryWarehouse [warehouseId=" + getWarehouseId() + ", capacity=" + getCapacity() + ", zipcode=" + getZipcode() + "]";
	}
}
