package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryTeacherCheckoutBook;

public class LibraryServiceTeacherCheckoutBook {

	private Connection connection;
	/**
	 * Get Database connection
	 */
	public LibraryServiceTeacherCheckoutBook(){
		 connection = DBUtility.getConnection();
	}
	
	/**
	 * Add teacher checkout book in teachercheckoutbook table
	 * @param book
	 */
	public void addTeacherAcceptedBook(LibraryTeacherCheckoutBook book) {
		
		try {
			String query = "Insert into teachercheckoutbook(userEmail, bookIsbn, checkoutDate)"
					+ " values (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, book.getUserEmail());
			statement.setString(2, book.getBookIsbn());
			statement.setDate(3, (Date) (book.getCheckoutDate()));

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
