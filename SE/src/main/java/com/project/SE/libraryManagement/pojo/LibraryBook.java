package com.project.SE.libraryManagement.pojo;

/**
 * Getters and setter for book table
 */
public class LibraryBook {

	private String bookName;
	private String author;
	private String ISBN; 
	private String publisher;
	private int noOfBooks;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	@Override
	public String toString() {
		return "LibraryBook [bookName=" + bookName + ", author=" + author + ", ISBN=" + ISBN + ", publisher="
				+ publisher + ", noOfBooks=" + noOfBooks + "]";
	}
	
	
}
