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
import com.project.SE.libraryManagement.pojo.LibraryStudentCalculatePenalty;

public class LibraryStudentServiceCalculatePenalty {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryStudentServiceCalculatePenalty(){
		connection = DBUtility.getConnection();
	}
	
	/**
	 * Insert into calculatePenalty table
	 * @param penalty
	 */
	public void addpenalty(LibraryStudentCalculatePenalty penalty){
		
		try {
			String query = "Insert into calculatePenalty(studentEmail, bookIsbn, actualReturnDate, penalty)"
					+ " values (?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, penalty.getStudentEmail());
			statement.setString(2, penalty.getBookIsbn());
			statement.setDate(3, (Date) penalty.getActualReturnDate());
			statement.setInt(4, penalty.getPenalty());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Search all the student with penalty 
	 * @return List of object
	 */
	public List<LibraryStudentCalculatePenalty> getAllstudentPenalty(){
		List<LibraryStudentCalculatePenalty> penalties = new ArrayList<LibraryStudentCalculatePenalty>();
		try{
			Statement statement = connection.createStatement();
			String query = "select studentEmail, sum(penalty) as penalty from calculatePenalty group by studentEmail";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				LibraryStudentCalculatePenalty penalty = new LibraryStudentCalculatePenalty();
				penalty.setStudentEmail(rs.getString("studentEmail"));
				penalty.setPenalty(rs.getInt("penalty"));
				penalties.add(penalty);
			}
		}catch(SQLException e){
			e.printStackTrace();
		};
		
		return penalties;
	}
}
