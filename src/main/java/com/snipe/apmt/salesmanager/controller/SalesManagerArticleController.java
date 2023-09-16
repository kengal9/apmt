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
import com.snipe.apmt.salesmanager.service.SalesManagerArticleService;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.service.UploaderArticleService;



@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")

public class SalesManagerArticleController {
	
	@Autowired
	SalesManagerArticleService salesManagerArticleService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderArticleService uploaderArticleService;
	@Autowired
	UploaderArticleRepository uploadedArticleRepository;
	
	@GetMapping(value= "/getAllArticleByCategory" )
	public ResponseEntity<GenericRes> getAllArticleByCategory() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}

	@GetMapping(value = "/allSalesArticles/{category_id}")
	public ResponseEntity<GenericRes> allSalesArticles(@PathVariable("category_id") int categoryID) throws Exception {
		
		return prepareSuccessResponse(salesManagerArticleService.getallSalesArticles(categoryID));
	}

	@GetMapping(value = "/salesNewArticles")
	public ResponseEntity<GenericRes> getVerifiedArticles() throws Exception {
		return prepareSuccessResponse(salesManagerArticleService.salesNewArticles());
	}
	
	@GetMapping(value = "/getArticleDetails/{article_id}")
	public ResponseEntity<GenericRes> getArticleDetails(@PathVariable("article_id") long articleId)
			throws Exception {
		//return prepareSuccessResponse(uploadedArticleRepository.getArticleByArticleId(articleId));
		return prepareSuccessResponse(uploaderArticleService.getArticleByArticleId(articleId));
	}
	
	@GetMapping(value = "/getArticleDetailed/{article_id}")
	public ResponseEntity<GenericRes> getArticleDetailed(@PathVariable("article_id") long articleId)
			throws Exception {
	return prepareSuccessResponse(uploaderArticleService.getArticleByArticleId(articleId));
	}
	
	@PutMapping(value="/addingArticlePrice/{article_id}/{editprice}")	
	public ResponseEntity<GenericRes> addingPrice(@PathVariable("article_id") long articleId,
			@PathVariable("editprice") long editprice) throws Exception{
		return prepareSuccessResponse(salesManagerArticleService.editPrice(articleId, editprice));
	}

}
