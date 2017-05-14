package com.project.SE.libraryManagement.pojo;

import java.sql.Date;

/**
 * Getters and setter for studentHavingTotalNoofBook table
 */
public class StudentHavingTotalNoofBook {

	private String studentEmail;
	private String bookIsbn;
	private Date returnDate;
	
	
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "StudentHavingTotalNoofBook [studentEmail=" + studentEmail + ", bookIsbn=" + bookIsbn + ", returnDate="
				+ returnDate + "]";
	}
	
	
}
