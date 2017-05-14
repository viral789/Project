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

import com.project.SE.libraryManagement.pojo.LibraryBook;
import com.project.SE.libraryManagement.service.LibraryServiceBook;

@Controller
public class LibraryControllerBook {

	LibraryServiceBook libraryServiceBook = new LibraryServiceBook();
	
	/**
	 * Return the view name that need to be load when we hit the url
	 * @return Sting 
	 */
	@RequestMapping(value="/")
	public String Main(){
		return "index";
	}
	
	/**
	 * Post Request to insert book details that admin enters into database
	 * @param book
	 */
	@RequestMapping(value="/books/insert", method = RequestMethod.POST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody void addBook(@RequestBody LibraryBook book){
		LibraryBook book2 = libraryServiceBook.getNoofBookCount(book.getISBN());
		if(book2.getBookName() == null ){
			libraryServiceBook.addBook(book);
		}
		else{
			int count = book2.getNoOfBooks() + book.getNoOfBooks();
			libraryServiceBook.updateNoofBookCount(book2.getISBN(), count);
		}
	}
	
	/**
	 * Get request to search selected book by name
	 * @param bookName
	 * @return book object
	 */
	@RequestMapping(value="/searchBook/{bookName}", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody LibraryBook getLibrarUserByEmailId(@PathVariable String bookName){
		LibraryBook book= libraryServiceBook.getBookByName(bookName);
		return book;
	}
}
