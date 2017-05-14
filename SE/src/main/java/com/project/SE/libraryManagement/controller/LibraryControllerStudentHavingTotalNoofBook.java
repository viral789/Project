package com.project.SE.libraryManagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.SE.libraryManagement.pojo.StudentHavingTotalNoofBook;
import com.project.SE.libraryManagement.service.LibraryServiceStudentHavingTotalNoofBook;

@Controller
public class LibraryControllerStudentHavingTotalNoofBook {

	LibraryServiceStudentHavingTotalNoofBook totalBooks = new LibraryServiceStudentHavingTotalNoofBook();
	
	/**
	 * Get request for returning total list of students having book
	 * @return List of object
	 */
	@RequestMapping(value="/getBooksWithStudent", method=RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<StudentHavingTotalNoofBook> getAllBookWithStudents(){
		List<StudentHavingTotalNoofBook> book = totalBooks.getAllBook();
		return book;
	}
}
