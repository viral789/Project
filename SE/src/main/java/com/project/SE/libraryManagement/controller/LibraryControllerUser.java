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
import com.project.SE.libraryManagement.pojo.LibraryUser;
import com.project.SE.libraryManagement.service.LibraryServiceRequestBook;
import com.project.SE.libraryManagement.service.LibraryServiceUser;

@Controller
public class LibraryControllerUser {
	
	LibraryServiceUser libraryServiceUser = new LibraryServiceUser();
	LibraryServiceRequestBook libraryServiceRequestBook = new LibraryServiceRequestBook();

	/**
	 * Post method to add user into database 
	 * @param user
	 * @return User Object
	 */
	@RequestMapping(value="/users/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryUser addUser(@RequestBody LibraryUser user){
		libraryServiceUser.addUser(user);
		LibraryUser users= libraryServiceUser.getUserByUserName(user.getEmailId(), user.getPassword());
		return users;
	}
	/**
	 * Post Request for authenticating user
	 * @param user
	 * @return user Object
	 */
	@RequestMapping(value="users/login", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryUser authenticate(@RequestBody LibraryUser user){
		LibraryUser users= libraryServiceUser.getUserByUserName(user.getEmailId(), user.getPassword());
		return users;
	}
	
	/**
	 * Get request to get user object using userId
	 * @param userId
	 * @return user object
	 */
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody LibraryUser getLibrarUserByEmailId(@PathVariable int userId){
		LibraryUser users= libraryServiceUser.getUserByUserId(userId);
		return users;
	}
	
	/**
	 * Put request to update user information 
	 * @param user
	 * @return user object
	 */
	@RequestMapping(value="/users/update", method = RequestMethod.PUT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryUser updateUser(@RequestBody LibraryUser user){
		libraryServiceUser.updateUser(user);
		LibraryUser users= libraryServiceUser.getUserByUserName(user.getEmailId(), user.getPassword());
		return users;
	}
}
