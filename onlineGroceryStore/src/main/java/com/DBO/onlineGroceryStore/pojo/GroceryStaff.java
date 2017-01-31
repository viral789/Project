package com.DBO.onlineGroceryStore.pojo;

public class GroceryStaff {
	private int userId;
	private String jobTitle;
	private int salary;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "GroceryStaff [userId=" + getUserId() + ", jobTitle=" + getJobTitle() + ", salary=" + getSalary()+ "]";
	}
	
	
}
