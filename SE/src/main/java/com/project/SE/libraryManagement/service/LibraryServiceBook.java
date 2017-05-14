package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryBook;

public class LibraryServiceBook {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryServiceBook(){
		 connection = DBUtility.getConnection();
	}
	
	/**
	 * Add book into book table using insert command
	 * @param book
	 */
	public void addBook(LibraryBook book) {
		
		try {
			String query = "Insert into book(bookName, author, ISBN, publisher, noOfBooks)"
					+ " values (?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, book.getBookName());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getISBN());
			statement.setString(4, book.getPublisher());
			statement.setInt(5, book.getNoOfBooks());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * search book table based on Name using select command
	 * @param bookName
	 * @return Object
	 */
	public LibraryBook getBookByName(String bookName) {
		LibraryBook book = new LibraryBook();
		try {
			String query = "select * from book where bookName = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bookName);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setBookName(resultSet.getString("bookName"));
					book.setAuthor(resultSet.getString("author"));
					book.setISBN(resultSet.getString("ISBN"));
					book.setPublisher(resultSet.getString("publisher"));
					book.setNoOfBooks(resultSet.getInt("noOfBooks"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	/** 
	 * search book table based on ISBN using select command
	 * @param ISBN
	 * @return Object
	 */
	public LibraryBook getNoofBookCount(String ISBN){
		LibraryBook book = new LibraryBook();
		try{
			String query = "select * from book where ISBN = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ISBN);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setBookName(resultSet.getString("bookName"));
					book.setAuthor(resultSet.getString("author"));
					book.setISBN(resultSet.getString("ISBN"));
					book.setPublisher(resultSet.getString("publisher"));
					book.setNoOfBooks(resultSet.getInt("noOfBooks"));
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * Update noOfBooks in book table using ISBN
	 * @param ISBN
	 * @param count
	 */
	public void updateNoofBookCount(String ISBN, int count){
		try{
			String query = "Update book set noOfBooks =? where ISBN = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, count);
			statement.setString(2, ISBN);
			
			statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	

}
