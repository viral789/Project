package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryStudentBookAccepted;

public class LibraryServiceStudentBookAccepted {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryServiceStudentBookAccepted(){
		 connection = DBUtility.getConnection();
	}
	
	/**
	 * Add accepted requested for student into acceptedStudentBook table
	 * @param book
	 */
	public void addStudentAcceptedBook(LibraryStudentBookAccepted book) {
		
		try {
			String query = "Insert into acceptedStudentBook(studentEmail, bookIsbn, status, acceptDate)"
					+ " values (?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, book.getStudentEmail());
			statement.setString(2, book.getbookIsbn());
			statement.setBoolean(3, book.isStatus());
			statement.setDate(4, (Date) book.getAcceptDate());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Search accepted Student Book request based on emailId
	 * @param emailId
	 * @return
	 */
	public LibraryStudentBookAccepted getStudentAcceptedBookByEmail(String emailId) {
		LibraryStudentBookAccepted book = new LibraryStudentBookAccepted();
		try {
			String query = "select * from acceptedStudentBook where studentEmail = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setStudentEmail(resultSet.getString("studentEmail"));
					book.setbookIsbn(resultSet.getString("bookIsbn"));
					book.setStatus(resultSet.getBoolean("status"));
					book.setAcceptDate(resultSet.getDate("acceptDate"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * Search accepted Student Book request based on emailId and Isbn
	 * @param emailId
	 * @param Isbn
	 * @return
	 */
	public LibraryStudentBookAccepted getStudentAcceptedBookByEmailAndIsbn(String emailId, String Isbn) {
		LibraryStudentBookAccepted book = new LibraryStudentBookAccepted();
		try {
			String query = "select * from acceptedStudentBook where studentEmail = ? and bookIsbn =?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, Isbn);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setStudentEmail(resultSet.getString("studentEmail"));
					book.setbookIsbn(resultSet.getString("bookIsbn"));
					book.setStatus(resultSet.getBoolean("status"));
					book.setAcceptDate(resultSet.getDate("acceptDate"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
}
