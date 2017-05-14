package com.project.SE.libraryManagement.pojo;

import java.sql.Date;

/**
 * Getters and setter for teacherCheckoutBook table
 */
public class LibraryTeacherCheckoutBook {

	private String userEmail;
	private String bookIsbn;
	private Date checkoutDate;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	@Override
	public String toString() {
		return "LibraryTeacherCheckoutBook [userEmail=" + userEmail + ", bookIsbn=" + bookIsbn + ", checkoutDate="
				+ checkoutDate + "]";
	}
	
	
}
