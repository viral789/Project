package com.project.SE.libraryManagement.pojo;

/**
 * Getters and setter for requestBook table
 */
public class LibraryRequestBook {

	private String studentName;
	private String studentEmail;
	private String bookName;
	private String bookIsbn;
	
	
	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getStudentEmail() {
		return studentEmail;
	}


	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBookIsbn() {
		return bookIsbn;
	}


	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}


	@Override
	public String toString() {
		return "LibraryRequestBook [studentName=" + studentName + ", studentEmail=" + studentEmail + ", bookName="
				+ bookName + ", bookIsbn=" + bookIsbn + "]";
	}
	
	
}

