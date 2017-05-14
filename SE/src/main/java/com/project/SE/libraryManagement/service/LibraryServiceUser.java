package com.project.SE.libraryManagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.SE.libraryManagement.model.DBUtility;
import com.project.SE.libraryManagement.pojo.LibraryUser;

public class LibraryServiceUser {

	private Connection connection;

	/**
	 * Get Database connection
	 */
	public LibraryServiceUser() {
		connection = DBUtility.getConnection();
	}

	/**
	 * Add user in users table
	 * @param libraryUser
	 * @return 1 if added successfully
	 */
	public int addUser(LibraryUser libraryUser) {
		int resultSet = 0;
		try {
			String query = "Insert into users(fName, lName, emailId, password, type, phoneNo)"
					+ " values (?,?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, libraryUser.getfName());
			statement.setString(2, libraryUser.getlName());
			statement.setString(3, libraryUser.getEmailId());
			statement.setString(4, libraryUser.getPassword());
			statement.setString(5, libraryUser.getType());
			statement.setString(6, libraryUser.getPhoneNo());

			resultSet = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * Search user from users table based on email and password 
	 * @param emailId
	 * @param password
	 * @return Object
	 */
	public LibraryUser getUserByUserName(String emailId, String password) {
		LibraryUser libraryUser = new LibraryUser();
		try {
			String query = "select * from users where emailId=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					libraryUser.setUserId(resultSet.getInt("userId"));
					libraryUser.setfName(resultSet.getString("fName"));
					libraryUser.setlName(resultSet.getString("lName"));
					libraryUser.setEmailId(resultSet.getString("emailId"));
					libraryUser.setPassword(resultSet.getString("password"));
					libraryUser.setType(resultSet.getString("type"));
					libraryUser.setPhoneNo(resultSet.getString("phoneNo"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryUser;
	}

	/**
	 * Update user table 
	 * @param libraryUser
	 * @return 1 id updated successfully
	 */
	public int updateUser(LibraryUser libraryUser) {
		int result = 0;

		try {
			String query = "update users set fName = ?, lName = ?, password = ?, phoneNo = ? where emailId = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, libraryUser.getfName());
			statement.setString(2, libraryUser.getlName());
			statement.setString(3, libraryUser.getPassword());
			statement.setString(4, libraryUser.getPhoneNo());
			statement.setString(5, libraryUser.getEmailId());

			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Search user table based on UserId
	 * @param userId
	 * @return Object
	 */
	public LibraryUser getUserByUserId(int userId) {
		LibraryUser libraryUser = new LibraryUser();
		try {
			String query = "select * from users where userId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					libraryUser.setUserId(resultSet.getInt("userId"));
					libraryUser.setfName(resultSet.getString("fName"));
					libraryUser.setlName(resultSet.getString("lName"));
					libraryUser.setEmailId(resultSet.getString("emailId"));
					libraryUser.setPassword(resultSet.getString("password"));
					libraryUser.setType(resultSet.getString("type"));
					libraryUser.setPhoneNo(resultSet.getString("phoneNo"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libraryUser;
	}
}
