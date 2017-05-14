package com.project.SE.libraryManagement.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.SE.libraryManagement.pojo.LibraryStudent;
import com.project.SE.libraryManagement.service.LibraryServiceStudent;

@Controller
public class LibraryControllerStudent {

	LibraryServiceStudent libraryServiceStudent = new LibraryServiceStudent();

	/**
	 * Post method to add student into database 
	 * @param user
	 * @return Student Object
	 */
	@RequestMapping(value="/student/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryStudent addStudent(@RequestBody LibraryStudent user){
		libraryServiceStudent.addUser(user);
		LibraryStudent users= libraryServiceStudent.getUserByEmailId(user.getEmailId());
		return users;
	}
	
	/**
	 * Get request to get student object using userId
	 * @param userId
	 * @return student object
	 */
	@RequestMapping(value="/student/{userId}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody LibraryStudent getLibrarUserByEmailId(@PathVariable int userId){
		LibraryStudent users= libraryServiceStudent.getUserByUserId(userId);
		return users;
	}
	
	/**
	 * Put request to update user information 
	 * @param user
	 * @return Student object
	 */
	@RequestMapping(value="/student/update", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryStudent updateStudent(@RequestBody LibraryStudent user){
		libraryServiceStudent.updateStudent(user);
		LibraryStudent users= libraryServiceStudent.getUserByEmailId(user.getEmailId());
		return users;
	}
}
