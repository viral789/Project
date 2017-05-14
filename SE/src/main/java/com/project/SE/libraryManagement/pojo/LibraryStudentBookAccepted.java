package com.project.SE.libraryManagement.pojo;

import java.sql.Date;

/**
 * Getters and setter for acceptedStudentBook table
 */
public class LibraryStudentBookAccepted {

	private String studentEmail;
	private String bookIsbn;
	private boolean status;
	private Date acceptDate;
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getbookIsbn() {
		return bookIsbn;
	}
	public void setbookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	@Override
	public String toString() {
		return "LibraryStudentBookAccepted [studentEmail=" + studentEmail + ", bookIsbn=" + bookIsbn + ", status=" + status
				+ ", acceptDate=" + acceptDate + "]";
	}
	
	
}
