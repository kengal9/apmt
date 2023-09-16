package com.snipe.apmt.verification.service;

import java.util.List;

import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.verification.model.VerificationBookModel;


public interface VerificationBookService  {
	
	public List<UploaderBookModel> getVerifiedBooks() throws Exception;

	public List<UploaderBookModel> getNewBooks() throws Exception;

	public List<UploaderBookModel> getBooksListByCategoryId(int categoryID) throws Exception;

// 	public String verifyBook(long bookId, boolean verifiedStatus) throws Exception;
	public String verifyBook(VerificationBookModel verificationBookModel) throws Exception;

	public String approveBook(long bookId, boolean approveStatus) throws Exception;

	public String rejectBook(long bookId, boolean rejectStatus) throws Exception;

	public List<VerificationBookModel> getVerificationBookDetails() throws Exception;
	
	public VerificationBookModel getVerificationBookDetailsByID(long bookId)throws Exception;

	

}
