package com.project.SE.libraryManagement.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.SE.libraryManagement.pojo.LibraryBook;
import com.project.SE.libraryManagement.pojo.LibraryStudentBookAccepted;
import com.project.SE.libraryManagement.service.LibraryServiceBook;
import com.project.SE.libraryManagement.service.LibraryServiceRequestBook;
import com.project.SE.libraryManagement.service.LibraryServiceStudentBookAccepted;
import com.project.SE.libraryManagement.service.LibraryServiceStudentHavingTotalNoofBook;

@Controller
public class LibraryControllerStudentBookAccepted {

	LibraryServiceStudentBookAccepted libraryServiceBookAccepted = new LibraryServiceStudentBookAccepted();
	LibraryServiceBook libraryServiceBook = new LibraryServiceBook();
	LibraryServiceStudentHavingTotalNoofBook havingBooks = new LibraryServiceStudentHavingTotalNoofBook();
	LibraryServiceRequestBook libraryServiceReqestBook = new LibraryServiceRequestBook();
	
	
	/**
	 * Post Request for accepting the student Request for Books and updating no of books count and deleting 
	 * book request as it is accepted by admin
	 * @param book
	 * @return book object
	 */
	@RequestMapping(value="/bookAccepted/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody LibraryStudentBookAccepted addStudentAcceptedBook(@RequestBody LibraryStudentBookAccepted book){
		libraryServiceBookAccepted.addStudentAcceptedBook(book);
		LibraryBook lbook = libraryServiceBook.getNoofBookCount(book.getbookIsbn());
		int count = lbook.getNoOfBooks()- 1; 
		libraryServiceBook.updateNoofBookCount(book.getbookIsbn(), count);
		libraryServiceReqestBook.deleteRequest(book.getStudentEmail(), book.getbookIsbn());
		Calendar c = Calendar.getInstance();
		c.setTime(book.getAcceptDate());
		c.add(Calendar.DATE, 7);
		Date returnDate = new Date(c.getTimeInMillis());
		havingBooks.addbooksWithStudent(book.getStudentEmail(),  book.getbookIsbn(), returnDate);
		
		LibraryStudentBookAccepted book1 = libraryServiceBookAccepted.getStudentAcceptedBookByEmail(book.getStudentEmail());
		return book1;
	}
	
	/**
	 * Post Method to delete the request when admin reject student request for book
	 * @param request
	 */
	@RequestMapping(value="/requestCancel", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody void deleteRequest ( @RequestBody LibraryStudentBookAccepted request){
		libraryServiceReqestBook.deleteRequest(request.getStudentEmail(), request.getbookIsbn());
	}
}
