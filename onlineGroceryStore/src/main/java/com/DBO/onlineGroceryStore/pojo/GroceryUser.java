package com.DBO.onlineGroceryStore.pojo;

public class GroceryUser {
	String userName;
	String passWord;
	int type;
	int userId;
	String firstName;
	String lastName;
	String eMailId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMailId() {
		return eMailId;
	}
	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}
	@Override
	public String toString() {
		return "GroceryUser [userName=" + getUserName() + ", passWord=" + getPassWord() + ", type=" + getType()+ ", userId=" + getUserId()
				+ ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", eMailId=" + geteMailId() + "]";
	}	
	
	
}
