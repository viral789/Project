package com.project.SE.libraryManagement.pojo;

/**
 * Getters and setter for user_student table
 */
public class LibraryStudent {

	private int UserId;
	private String EmailId;
	private int noOfcoursesRegister;
	private String parentName;
	private String parentEmail;
	private String parentNo;
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public int getNoOfcoursesRegister() {
		return noOfcoursesRegister;
	}
	public void setNoOfcoursesRegister(int noOfcoursesRegister) {
		this.noOfcoursesRegister = noOfcoursesRegister;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getParentNo() {
		return parentNo;
	}
	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}
	
	@Override
	public String toString() {
		return "LibraryStudent [UserId=" + UserId + ", EmailId=" + EmailId + ", noOfcoursesRegister="
				+ noOfcoursesRegister + ", parentName=" + parentName + ", parentEmail=" + parentEmail + ", parentNo="
				+ parentNo + "]";
	}
	
	
}
