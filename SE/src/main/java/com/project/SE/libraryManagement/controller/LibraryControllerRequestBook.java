package com.project.SE.libraryManagement.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.SE.libraryManagement.pojo.LibraryRequestBook;
import com.project.SE.libraryManagement.service.LibraryServiceRequestBook;

@Controller
public class LibraryControllerRequestBook {

	LibraryServiceRequestBook libraryServiceRequestBook = new LibraryServiceRequestBook();
	
	/**
	 * Post request to insert the books which user request for. It can be more than one book
	 * @param request
	 */
	@RequestMapping(value="/requests/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody void addBookRequest(@RequestBody List<LibraryRequestBook> request){
		for(LibraryRequestBook request2: request){
			libraryServiceRequestBook.addbookRequest(request2);
		}
	}
	
	/**
	 * Get request to get all the requested book by user
	 * @return
	 */
	@RequestMapping(value="/allBookRequest", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<LibraryRequestBook> getAllstudentBookRequest (){
		List<LibraryRequestBook> request = libraryServiceRequestBook.getAllstudentBookRequest();
		return request;
	}
	
	
}
