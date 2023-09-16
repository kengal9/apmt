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
import com.snipe.apmt.uploader.service.UploaderArticleService;
import com.snipe.apmt.verification.model.VerificationArticleModel;
import com.snipe.apmt.verification.model.VerificationModel;
import com.snipe.apmt.verification.service.VerificationArticleService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class VerificationArticleController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderArticleService uploaderArticleService;	
	@Autowired
	VerificationArticleService verificationArticleService;
	
	@GetMapping(value = "/getAllArticles")
	public ResponseEntity<GenericRes> getAllArticles() throws Exception {
		return prepareSuccessResponse(uploaderArticleService.getListByArticleId());
	}

	@GetMapping(value = "/getVerifiedArticles")
	public ResponseEntity<GenericRes> getVerifiedArticles() throws Exception {
		return prepareSuccessResponse(verificationArticleService.getVerifiedArticles());
	}
	
	@GetMapping(value = "/getNewArticles")
	public ResponseEntity<GenericRes> getNewArticles() throws Exception {
		return prepareSuccessResponse(verificationArticleService.getNewArticles());
	}

	@GetMapping(value = "/getArticleCategories")
	public ResponseEntity<GenericRes> getArticleCategories() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}
	
	@GetMapping(value = "/getArticlesByCategoryId/{category_id}")
	public ResponseEntity<GenericRes> getArticlesByCategoryId(@PathVariable("category_id") int categoryID)
			throws Exception {
		return prepareSuccessResponse(verificationArticleService.getArticlesListByCategoryId(categoryID));
	}

	@GetMapping(value = "/getArticleDetailsById/{article_id}")
	public ResponseEntity<GenericRes> getArticleDetailsById(@PathVariable("article_id") long articleId)
			throws Exception {
		return prepareSuccessResponse(uploaderArticleService.getArticleByArticleId(articleId));
	}
	
//	@PutMapping(value = "/verifyArticle/{article_id}/{verifiedStatus}")
//	public ResponseEntity<GenericRes> verifyArticle(@PathVariable("article_id") long articleId,
//			@PathVariable("verifiedStatus") boolean verifiedStatus) throws Exception {
//		return prepareSuccessResponse(verificationArticleService.verifyArticle(articleId, verifiedStatus));
//	}
	
	@PostMapping(value = "/verifyArticle")
	public ResponseEntity<GenericRes> verifyProject(@RequestBody VerificationArticleModel verificationArticleModel) throws Exception {
		return prepareSuccessResponse(verificationArticleService.verifyArticle(verificationArticleModel));
	}

	@PutMapping(value = "/approveArticle/{article_id}/{approveStatus}")
	public ResponseEntity<GenericRes> approveArticle(@PathVariable("article_id") long articleId,
			@PathVariable("approveStatus") boolean approveStatus) throws Exception {
		return prepareSuccessResponse(verificationArticleService.approveArticle(articleId, approveStatus));
	}

	@PutMapping(value = "/rejectArticle/{article_id}/{rejectStatus}")
	public ResponseEntity<GenericRes> rejectArticle(@PathVariable("article_id") long articleId,
			@PathVariable("rejectStatus") boolean rejectStatus) throws Exception {
		return prepareSuccessResponse(verificationArticleService.rejectArticle(articleId, rejectStatus));
	}

	@GetMapping(value = "/getVerificationArticleDetails")
	public ResponseEntity<GenericRes> getVerificationArticleDetails() throws Exception {
		return prepareSuccessResponse(verificationArticleService.getVerificationArticleDetails());
	}

	@GetMapping(value = "/getVerificationArticleDetailsByID/{article_id}")
	public ResponseEntity<GenericRes> getVerificationArticleDetailsByID(@PathVariable("article_id") long articleId)
			throws Exception {
		return prepareSuccessResponse(verificationArticleService.getVerificationArticleDetailsByID(articleId));
	}

}
