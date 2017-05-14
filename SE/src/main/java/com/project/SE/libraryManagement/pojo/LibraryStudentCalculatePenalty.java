package com.project.SE.libraryManagement.pojo;

import java.sql.Date;

/**
 * Getters and setter for calculatPenalty table
 */
public class LibraryStudentCalculatePenalty {

	private String studentEmail;
	private String bookIsbn;
	private Date actualReturnDate;
	private int penalty;
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	@Override
	public String toString() {
		return "LibraryStudentCalculatePenalty [studentEmail=" + studentEmail + ", bookIsbn=" + bookIsbn
				+ ", actualReturnDate=" + actualReturnDate + ", penalty=" + penalty + "]";
	}
	
	
}
