package com.snipe.apmt.verification.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.service.UploaderBookService;
import com.snipe.apmt.verification.model.VerificationBookModel;
import com.snipe.apmt.verification.service.VerificationBookService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class VerificationBookController {

	//@Autowired(required = false)
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderBookService uploaderBookService;	
	@Autowired
	VerificationBookService verificationBookService;

	@GetMapping(value = "/getAllBooks")
	public ResponseEntity<GenericRes> getAllBooks() throws Exception {
		return prepareSuccessResponse(uploaderBookService.getBooksList());
	}

	@GetMapping(value = "/getVerifiedBooks")
	public ResponseEntity<GenericRes> getVerifiedBooks() throws Exception {
		return prepareSuccessResponse(verificationBookService.getVerifiedBooks());
	}

	@GetMapping(value = "/getNewBooks")
	public ResponseEntity<GenericRes> getNewBooks() throws Exception {
		return prepareSuccessResponse(verificationBookService.getNewBooks());
	}

	@GetMapping(value = "/getBookCategories")
	public ResponseEntity<GenericRes> getBookCategories() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}

	@GetMapping(value = "/getBooksByCategoryId/{category_id}")
	public ResponseEntity<GenericRes> getBooksByCategoryId(@PathVariable("category_id") int categoryID)
			throws Exception {
		return prepareSuccessResponse(verificationBookService.getBooksListByCategoryId(categoryID));
	}

	@GetMapping(value = "/getBookDetailsById/{book_id}")
	public ResponseEntity<GenericRes> getBookDetailsById(@PathVariable("book_id") long bookId)
			throws Exception {
		return prepareSuccessResponse(uploaderBookService.getBookByBookId(bookId));
	}

//	@PutMapping(value = "/verifyBook/{book_id}/{verifiedStatus}")
//	public ResponseEntity<GenericRes> verifyBook(@PathVariable("book_id") long bookId,
//			@PathVariable("verifiedStatus") boolean verifiedStatus) throws Exception {
//		return prepareSuccessResponse(verificationBookService.verifyBook(bookId, verifiedStatus));
//	}
	
	@PostMapping(value = "/verifyBook")
	public ResponseEntity<GenericRes> verifyBook(@RequestBody VerificationBookModel verificationBookModel) throws Exception {
		return prepareSuccessResponse(verificationBookService.verifyBook(verificationBookModel));
	}

	@PutMapping(value = "/approveBook/{book_id}/{approveStatus}")
	public ResponseEntity<GenericRes> approveBook(@PathVariable("book_id") long bookId,
			@PathVariable("approveStatus") boolean approveStatus) throws Exception {
		return prepareSuccessResponse(verificationBookService.approveBook(bookId, approveStatus));
	}

	@PutMapping(value = "/rejectBook/{book_id}/{rejectStatus}")
	public ResponseEntity<GenericRes> rejectBook(@PathVariable("book_id") long bookId,
			@PathVariable("rejectStatus") boolean rejectStatus) throws Exception {
		return prepareSuccessResponse(verificationBookService.rejectBook(bookId, rejectStatus));
	}

	@GetMapping(value = "/getVerificationBookDetails")
	public ResponseEntity<GenericRes> getVerificationBookDetails() throws Exception {
		return prepareSuccessResponse(verificationBookService.getVerificationBookDetails());
	}

	@GetMapping(value = "/getVerificationBookDetailsByID/{book_id}")
	public ResponseEntity<GenericRes> getVerificationBookDetailsByID(@PathVariable("book_id") long bookId)
			throws Exception {
		return prepareSuccessResponse(verificationBookService.getVerificationBookDetailsByID(bookId));
	}


	
}
