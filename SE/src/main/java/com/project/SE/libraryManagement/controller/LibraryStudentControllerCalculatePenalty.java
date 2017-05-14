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

import com.project.SE.libraryManagement.pojo.LibraryStudentBookAccepted;
import com.project.SE.libraryManagement.pojo.LibraryStudentCalculatePenalty;
import com.project.SE.libraryManagement.pojo.StudentHavingTotalNoofBook;
import com.project.SE.libraryManagement.service.LibraryStudentServiceCalculatePenalty;
import com.project.SE.libraryManagement.service.LibraryServiceStudentBookAccepted;
import com.project.SE.libraryManagement.service.LibraryServiceStudentHavingTotalNoofBook;

@Controller
public class LibraryStudentControllerCalculatePenalty {

	LibraryServiceStudentHavingTotalNoofBook bookList = new LibraryServiceStudentHavingTotalNoofBook();
	LibraryStudentServiceCalculatePenalty servicePenaty = new LibraryStudentServiceCalculatePenalty();
	LibraryServiceStudentBookAccepted accepted = new LibraryServiceStudentBookAccepted();
	LibraryServiceStudentHavingTotalNoofBook books = new LibraryServiceStudentHavingTotalNoofBook();
	
	/**
	 * Post Request to add when did the user returned the book
	 * @param penalty
	 */
	@RequestMapping(value="/return/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody void addBookRequest(@RequestBody LibraryStudentCalculatePenalty penalty){
		LibraryStudentBookAccepted accept = accepted.getStudentAcceptedBookByEmailAndIsbn(penalty.getStudentEmail(), penalty.getBookIsbn());
		StudentHavingTotalNoofBook haveBook = books.getBookByEmailandISBN(penalty.getStudentEmail(), penalty.getBookIsbn());
		long acceptDate = accept.getAcceptDate().getTime();
		long returnDate = haveBook.getReturnDate().getTime();
		long days = returnDate - acceptDate;
		int diffenceDays = (int)(days / 604800000); 
		if(diffenceDays > 7){
			int penalty1 = (diffenceDays*10);
			penalty.setPenalty(penalty1);
		}
		servicePenaty.addpenalty(penalty);
		books.deleteBook(penalty.getStudentEmail(), penalty.getBookIsbn());
		
	}
	
	/**
	 * Get request to get penalty each student need to pay 
	 * @return List of Objects
	 */
	@RequestMapping(value="/getallStudentPenalty", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<LibraryStudentCalculatePenalty> getAllstudentPenalty (){
		List<LibraryStudentCalculatePenalty> penalty = servicePenaty.getAllstudentPenalty();
		return penalty;
	}
}
