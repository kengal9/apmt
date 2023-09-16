package com.snipe.apmt.uploader.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;



public interface UploaderBookService {
	
	public String saveBook(UploaderBookModel uploaderBookModel,MultipartFile image) throws Exception;

	public List<UploaderBookModel> getBooksList() throws Exception;
	
	public List<UploaderBookModel> getBooksListById(long bookId) throws Exception;
	
	public String deleteByBookId(long bookId) throws Exception;
	
	public String UploadBookInStorageService(MultipartFile file,String fileNameAndPath) throws Exception;

	public String deleteBook(String bookName) throws Exception;
	
	//Created for purpose of uploading/downloading book 
	public UploaderBookModel getBookByBookId(long bookId) throws Exception;

	public byte[] downloadBook(String bookName) throws Exception;

	public List<UploaderBookModel> getApprovedBooks();

	
		
		
 

}
