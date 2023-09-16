package com.snipe.apmt.salesmanager.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.salesmanager.service.SalesManagerBookService;
import com.snipe.apmt.uploader.service.UploaderBookService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")

public class SalesManagerBookController {

	@Autowired
	SalesManagerBookService salesManagerBookService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderBookService uploaderBookService;
	
	@GetMapping(value= "/getAllBooksByCategory" )
	public ResponseEntity<GenericRes> getAllBooksByCategory() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}
	
	@GetMapping(value = "/allSalesBooks/{category_id}")
	public ResponseEntity<GenericRes> allSalesBooks(@PathVariable("category_id") int categoryID) throws Exception {
		
		return prepareSuccessResponse(salesManagerBookService.getallSalesBooks(categoryID));
	}
	
	@GetMapping(value = "/salesNewBooks")
	public ResponseEntity<GenericRes> getVerifiedBooks() throws Exception {
		return prepareSuccessResponse(salesManagerBookService.salesNewBooks());
	}
	
	@GetMapping(value = "/getBookDetails/{book_id}")
	public ResponseEntity<GenericRes> getBookDetails(@PathVariable("book_id") long bookId)
			throws Exception {
		return prepareSuccessResponse(uploaderBookService.getBookByBookId(bookId));
	}
	
	@PutMapping(value="/addingbookPrice/{book_id}/{editprice}")		
	public ResponseEntity<GenericRes> addingPrice(@PathVariable("book_id") long bookId,
			@PathVariable("editprice") long editprice) throws Exception{
		return prepareSuccessResponse(salesManagerBookService.editPrice(bookId, editprice));
		}
	}
