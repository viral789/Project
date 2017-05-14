package com.project.SE.libraryManagement.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryRequestBook;

public class LibraryServiceRequestBook {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryServiceRequestBook(){
		connection = DBUtility.getConnection();
	}
	/**
	 * Add Requested book into bookRequest table using insert command
	 * @param request
	 */
	public void addbookRequest(LibraryRequestBook request){
		try{
			String query = "Insert into bookRequest (studentName, studentEmail, bookName, bookIsbn) values(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, request.getStudentName());
			statement.setString(2, request.getStudentEmail());
			statement.setString(3, request.getBookName());
			statement.setString(4, request.getBookIsbn());
			
			statement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Search requested book based on emailID  
	 * @param emailId
	 * @return Object
	 */
	public LibraryRequestBook getRequestByEmailId(String emailId) {
		LibraryRequestBook request = new LibraryRequestBook();
		try {
			String query = "select * from bookRequest where studentEmail = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					request.setStudentName(resultSet.getString("studentName"));
					request.setStudentEmail(resultSet.getString("studentEmail"));
					request.setBookName(resultSet.getString("bookName"));
					request.setBookIsbn(resultSet.getString("bookIsbn"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}
	
	/**
	 * Select all the Requested book of all the students 
	 * @return List of Object
	 */
	public List<LibraryRequestBook> getAllstudentBookRequest(){
		List<LibraryRequestBook> libraryRequestBooks = new ArrayList<LibraryRequestBook>();
		try{
			Statement statement = connection.createStatement();
			String query = "select * from bookRequest";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				LibraryRequestBook request = new LibraryRequestBook();
				request.setStudentName(rs.getString("studentName"));
				request.setStudentEmail(rs.getString("studentEmail"));
				request.setBookName(rs.getString("bookName"));
				request.setBookIsbn(rs.getString("bookIsbn"));
				
				libraryRequestBooks.add(request);
			}
		}catch(SQLException e){
			e.printStackTrace();
		};
		
		return libraryRequestBooks;
	}
	
	/**
	 * Delete request based on emailId and ISBN from bookRequest once book is accepted or rejected
	 * @param email
	 * @param ISBN
	 */
	public void deleteRequest(String email, String ISBN){
		try {
			String query = "delete from bookRequest where studentEmail = ? and bookIsbn = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, ISBN);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
