package com.project.SE.libraryManagement.pojo;

/**
 * Getters and setter for User table
 */
public class LibraryUser {

	private String fName;
	private String lName;
	private String password;
	private String emailId;
	private String type;
	private String phoneNo;
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "LibraryUser [fName=" + fName + ", lName=" + lName + ", password=" + password + ", emailId=" + emailId
				+ ", type=" + type + ", phoneNo=" + phoneNo + ", userId=" + userId + "]";
	}
	
}
