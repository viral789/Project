package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryStudent;

public class LibraryServiceStudent {

	private Connection connection;
	
	/**
	 * Get Database connection
	 */
	public LibraryServiceStudent(){
		connection = DBUtility.getConnection();
	}
	
	/**
	 * Insert Student into Student table
	 * @param libraryStudent
	 * @return
	 */
	public int addUser(LibraryStudent libraryStudent){
		int resultSet = 0;
		try{
			String query = "Insert into user_student(userId, emailId, noOfcoursesRegister, parentName, parentEmail, parentNo)"
					+ " values (?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, libraryStudent.getUserId());
			statement.setString(2, libraryStudent.getEmailId());
			statement.setInt(3, libraryStudent.getNoOfcoursesRegister());
			statement.setString(4, libraryStudent.getParentName());
			statement.setString(5, libraryStudent.getParentEmail());
			statement.setString(6, libraryStudent.getParentNo());
			
			resultSet = statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Search student from student table base on userId
	 * @param userId
	 * @return Object
	 */
	public LibraryStudent getUserByUserId (int userId){
		LibraryStudent libraryStudent = new LibraryStudent();
		try{
			String query = "select * from user_student where userId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next()){
					libraryStudent.setUserId(resultSet.getInt("userId"));
					libraryStudent.setEmailId(resultSet.getString("emailId"));
					libraryStudent.setNoOfcoursesRegister(resultSet.getInt("noOfcoursesRegister"));
					libraryStudent.setParentName(resultSet.getString("parentName"));
					libraryStudent.setParentEmail(resultSet.getString("parentEmail"));
					libraryStudent.setParentNo(resultSet.getString("parentNo"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryStudent;
	}
	
	/**
	 * Search student from student table base on emailId
	 * @param emailId
	 * @return Object
	 */
	public LibraryStudent getUserByEmailId (String emailId){
		LibraryStudent libraryStudent = new LibraryStudent();
		try{
			String query = "select * from user_student where emailId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet != null){
				while(resultSet.next()){
					libraryStudent.setUserId(resultSet.getInt("userId"));
					libraryStudent.setEmailId(resultSet.getString("emailId"));
					libraryStudent.setNoOfcoursesRegister(resultSet.getInt("noOfcoursesRegister"));
					libraryStudent.setParentName(resultSet.getString("parentName"));
					libraryStudent.setParentEmail(resultSet.getString("parentEmail"));
					libraryStudent.setParentNo(resultSet.getString("parentNo"));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryStudent;
	}
	
	/**
	 * Update student Table
	 * @param libraryStudent
	 * @return 1 if updated successfully
	 */
	public int updateStudent(LibraryStudent libraryStudent)
	{
		int result= 0;	
		try {
			String query = "update user_student set noOfcoursesRegister = ?, parentName = ?, parentEmail = ?, parentNo = ? where emailId = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, libraryStudent.getNoOfcoursesRegister());
			statement.setString(2, libraryStudent.getParentName());
			statement.setString(3, libraryStudent.getParentEmail());
			statement.setString(4, libraryStudent.getParentNo());
			statement.setString(5, libraryStudent.getEmailId());
						
			result = statement.executeUpdate();
		} catch (SQLException e) {		
			e.printStackTrace();
		}	
		return result;
	}
	
}
