package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.StudentHavingTotalNoofBook;

public class LibraryServiceStudentHavingTotalNoofBook {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryServiceStudentHavingTotalNoofBook(){
		 connection = DBUtility.getConnection();
	}
	
	/**
	 * Add books which student has into studentHavingTotalNoofBook table
	 * @param email
	 * @param ISBN
	 * @param returnDate
	 */
	public void addbooksWithStudent(String email, String ISBN, Date returnDate) {
		
		try {
			String query = "Insert into studentHavingTotalNoofBook(studentEmail, bookIsbn, returnDate)"
					+ " values (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, ISBN);
			statement.setDate(3, (Date) returnDate);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Search student which has books in studentHavingTotalNoofBook table based on email id 
	 * @param emailId
	 * @return
	 */
	public StudentHavingTotalNoofBook getBookByEmail(String emailId) {
		StudentHavingTotalNoofBook book = new StudentHavingTotalNoofBook();
		try {
			String query = "select * from studentHavingTotalNoofBook where studentEmail = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);
			

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setStudentEmail(resultSet.getString("studentEmail"));
					book.setBookIsbn(resultSet.getString("bookIsbn"));
					book.setReturnDate(resultSet.getDate("returnDate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * Search student which has books in studentHavingTotalNoofBook table based on email id  and ISBN
	 * @param emailId
	 * @param ISBN
	 * @return
	 */
	public StudentHavingTotalNoofBook getBookByEmailandISBN(String emailId, String ISBN) {
		StudentHavingTotalNoofBook book = new StudentHavingTotalNoofBook();
		try {
			String query = "select * from studentHavingTotalNoofBook where studentEmail = ? and bookIsbn =?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, ISBN);
			

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					book.setStudentEmail(resultSet.getString("studentEmail"));
					book.setBookIsbn(resultSet.getString("bookIsbn"));
					book.setReturnDate(resultSet.getDate("returnDate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	/**
	 * Search all the student having book 
	 * @return list of Object
	 */
	public List<StudentHavingTotalNoofBook> getAllBook() {
		
		List<StudentHavingTotalNoofBook> books = new ArrayList<StudentHavingTotalNoofBook>();
		
		try {
			Statement statement = connection.createStatement();
			String query = "select * from studentHavingTotalNoofBook";
			
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				StudentHavingTotalNoofBook book = new StudentHavingTotalNoofBook();
				book.setStudentEmail(rs.getString("studentEmail"));
				book.setBookIsbn(rs.getString("bookIsbn"));
				book.setReturnDate(rs.getDate("returnDate"));
				
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	/**
	 * Delete book when book is returned 
	 * @param email
	 * @param Isbn
	 */
	public void deleteBook(String email, String Isbn){
		try {
			String query = "delete from studentHavingTotalNoofBook where studentEmail= ? and bookIsbn = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, Isbn);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
