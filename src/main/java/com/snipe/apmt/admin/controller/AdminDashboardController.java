package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.service.AdminDashboardService;
import com.snipe.apmt.exception.GenericRes;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings("rawtypes")
public class AdminDashboardController {
	
	@Autowired
	AdminDashboardService adminDashboardService;
	
	@GetMapping(value = "/admin/newProjectList")
	public ResponseEntity<GenericRes> getListNewProject() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listNewProject());
	}

	@GetMapping(value = "/admin/oldProjectList")
	public ResponseEntity<GenericRes> getListOldProject() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listOldProject());
	}
	
	@GetMapping(value = "/admin/countAllRecords")
	public ResponseEntity<GenericRes> getCountRecords() throws Exception {
		return prepareSuccessResponse(adminDashboardService.countOfRecords());
	}
	
	@GetMapping(value = "/admin/newBookList")
	public ResponseEntity<GenericRes> getListNewBook() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listNewBook());
	}

	@GetMapping(value = "/admin/oldBookList")
	public ResponseEntity<GenericRes> getListOldBook() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listOldBook());
	}
	
	@GetMapping(value = "/admin/countAllBookRecords")
	public ResponseEntity<GenericRes> getCountBookRecords() throws Exception {
		return prepareSuccessResponse(adminDashboardService.countOfBookRecords());
	}
	
	@GetMapping(value = "/admin/newArticleList")
	public ResponseEntity<GenericRes> getListNewArticle() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listNewArticle());
	}

	@GetMapping(value = "/admin/oldArticleList")
	public ResponseEntity<GenericRes> getListOldArticle() throws Exception {
		return prepareSuccessResponse(adminDashboardService.listOldArticle());
	}
	
	@GetMapping(value = "/admin/countAllArticleRecords")
	public ResponseEntity<GenericRes> getCountArticleRecords() throws Exception {
		return prepareSuccessResponse(adminDashboardService.countOfArticleRecords());
	}

	
}
