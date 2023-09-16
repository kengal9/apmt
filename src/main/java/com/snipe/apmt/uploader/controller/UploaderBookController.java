package com.snipe.apmt.uploader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.service.UploaderBookService;
import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class UploaderBookController {

	@Autowired
	UploaderBookService uploaderBookService;
	
	@Value("${apmt.apmtUrl}")
	private String uriPath;
	
	public UploaderBookModel getJson(String uploaderBookModel) {
		UploaderBookModel uploaderBookJson=new UploaderBookModel();
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			uploaderBookJson=objectMapper.readValue(uploaderBookModel, UploaderBookModel.class);
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			
		}
		return uploaderBookJson;
	}
	@GetMapping(value ="/booksList")
	public ResponseEntity<GenericRes>getBooksList()throws Exception{
		return prepareSuccessResponse(uploaderBookService.getBooksList());		
	}
	
	@GetMapping(value="/booksList/{bookId}")
	public ResponseEntity<GenericRes>getUploadedBooksListById(@PathVariable("bookId") long bookId) throws Exception{
		return prepareSuccessResponse(uploaderBookService.getBookByBookId(bookId));
		
	}
	
	@DeleteMapping(value = "/deleteBook/{bookId}")
	public ResponseEntity<GenericRes> deleteByBookId(@PathVariable("bookId") long bookId) throws Exception {
		return prepareSuccessResponse(uploaderBookService.deleteByBookId(bookId));
	}
	
	@PostMapping("/uploadBook")
	public ResponseEntity<GenericRes>  uploadBookInStorage(@RequestParam("File") MultipartFile file,String fileNameAndPath)throws Exception {
		return prepareSuccessResponse(uploaderBookService.UploadBookInStorageService(file,fileNameAndPath));
    }
	
	@GetMapping("/downloadbook")
	public ResponseEntity<?>  downloadBook(@RequestParam String bookName)throws Exception {
		byte[] data =uploaderBookService.downloadBook(bookName);
		//ByteArrayResource resource=new ByteArrayResource(data);		
		return ResponseEntity.status(HttpStatus.OK)
				.contentLength(data.length)
				.header("Content-type","application/octet-stream")
				.header("Content-disposition", "attachment; bookName=\""+bookName +"\"")
				.body(data);			
				
    }
	@PostMapping(value = "/save/Book", consumes = { "application/json", "multipart/form-data" })
	public ResponseEntity<GenericRes> saveBook(@RequestPart("uploaderBookModel") String uploaderBookModel,@RequestParam("Image") MultipartFile file) throws Exception {
		
		UploaderBookModel  uploaderBookModel1= getJson(uploaderBookModel);
			
		return prepareSuccessResponse(uploaderBookService.saveBook(uploaderBookModel1,file));
	}
	
	@GetMapping(value = "/approvedBooks")
	public ResponseEntity<GenericRes> getApprovedBooks() throws Exception {
		return prepareSuccessResponse(uploaderBookService.getApprovedBooks());
	}
}
