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

import com.project.SE.libraryManagement.pojo.LibraryBook;
import com.project.SE.libraryManagement.pojo.LibraryTeacherCheckoutBook;
import com.project.SE.libraryManagement.service.LibraryServiceBook;
import com.project.SE.libraryManagement.service.LibraryServiceRequestBook;
import com.project.SE.libraryManagement.service.LibraryServiceStudentHavingTotalNoofBook;
import com.project.SE.libraryManagement.service.LibraryServiceTeacherCheckoutBook;

@Controller
public class LibraryControllerTeacherCheckoutBook {

	LibraryServiceTeacherCheckoutBook techerCheckout = new LibraryServiceTeacherCheckoutBook();
	LibraryServiceBook libraryServiceBook = new LibraryServiceBook();
	LibraryServiceStudentHavingTotalNoofBook havingBooks = new LibraryServiceStudentHavingTotalNoofBook();
	LibraryServiceRequestBook libraryServiceReqestBook = new LibraryServiceRequestBook();
	
	/**
	 * Post Request to check out book by teacher and updating number of book count into database
	 * @param book
	 */
	@RequestMapping(value="/teacherCheckout/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody void addBookCheckout(@RequestBody List<LibraryTeacherCheckoutBook> book){
		for(LibraryTeacherCheckoutBook book2: book){
			techerCheckout.addTeacherAcceptedBook(book2);
			LibraryBook lbook = libraryServiceBook.getNoofBookCount(book2.getBookIsbn());
			int count = lbook.getNoOfBooks()- 1; 
			libraryServiceBook.updateNoofBookCount(book2.getBookIsbn(), count);
		}
	}
	
}
