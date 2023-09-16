package com.snipe.apmt.uploader.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.service.UploaderArticleService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class UploaderArticleController {

	@Autowired
	UploaderArticleService uploaderArticleService;

	@Value("${apmt.apmtUrl}")
	private String uriPath;

	public UploaderArticleModel getJson(String uploaderArticleModel) {
		UploaderArticleModel uploaderJson = new UploaderArticleModel();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			uploaderJson = objectMapper.readValue(uploaderArticleModel, UploaderArticleModel.class);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
		return uploaderJson;
	}

	@PostMapping(value = "/save/article", consumes = { "application/json", "multipart/form-data" })
	public ResponseEntity<GenericRes> saveArticleDetails(@RequestPart String uploderArticleModel,
			@RequestParam(value = "image") MultipartFile image)
			throws Exception {
		UploaderArticleModel uploaderArticleModel1 = getJson(uploderArticleModel);
		return prepareSuccessResponse(uploaderArticleService.saveArticle(uploaderArticleModel1, image));
	}

//	@GetMapping(value="/dwnloadArticle/{name}")
//	public ResponseEntity<GenericRes> download(@PathVariable("name") String name) throws Exception{
//		Resource downloadFile = uploaderArticleService.downloadFile(name);
//		InputStream data =this.getClass().getClassLoader().getResourceAsStream("C:\\apmt\\apmt_backend\\apmt_backend\\src\\main\\resources\\Images");
//		return prepareSuccessResponse(data);
//	}

	@GetMapping(value = "/downloadArticle")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) throws Exception {
		String nameWithPath = "Article/Image/" + fileName;
		byte[] data = uploaderArticleService.downloadFile(nameWithPath);
		// ByteArrayResource resource=new ByteArrayResource(data);
		return ResponseEntity.status(HttpStatus.OK).contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(data);
	}

	@PostMapping("/uploadArticleFile")
	public ResponseEntity<GenericRes> uploadFileInStorage(@RequestParam("File") MultipartFile file,
			String fileNameAndPath) throws Exception {
		return prepareSuccessResponse(uploaderArticleService.UploadFileInStorageService(file, fileNameAndPath));
	}

	@DeleteMapping("/deleteFile/{filename}")
	public ResponseEntity<GenericRes> uploadFileInStorage(@PathVariable("filename") String fileName) throws Exception {
		return prepareSuccessResponse(uploaderArticleService.deleteFile(fileName));
	}

	@GetMapping(value = "/getArticle/{articleId}")
	public ResponseEntity<GenericRes> getUploadedArticleDetailsById(@PathVariable("articleId") long articleId)
			throws Exception {
		return prepareSuccessResponse(uploaderArticleService.getArticleByArticleId(articleId));
	}

	@PutMapping(value = "/updateArticle")
	public ResponseEntity<GenericRes> updateArticle(@RequestBody UploaderArticleModel uploderArticleModel)
			throws Exception {
		return prepareSuccessResponse(uploaderArticleService.updateArticle(uploderArticleModel));

	}

	@DeleteMapping(value = "/deleteArticle/{articleId}")
	public ResponseEntity<GenericRes> deleteByArticleId(@PathVariable("articleId") long articleId) throws Exception {
		return prepareSuccessResponse(uploaderArticleService.deleteByArticleId(articleId));
	}

	@GetMapping(value = "/articleList")
	public ResponseEntity<GenericRes> getUploadedArticleListById() throws Exception {
		return prepareSuccessResponse(uploaderArticleService.getListByArticleId());
	}
	
	@GetMapping(value = "/approvedArticles")
	public ResponseEntity<GenericRes> getApprovedArticles() throws Exception {
		return prepareSuccessResponse(uploaderArticleService.getApprovedArticles());
	}
}
