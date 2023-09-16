package com.snipe.apmt.salesmanager.controller;

import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.salesmanager.service.SalesManagerService;
import com.snipe.apmt.uploader.service.UploaderService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")

public class SalesManagerController {

	@Autowired
	SalesManagerService salesManagerService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderService uploaderService;
	
	
	
	@GetMapping(value= "/getCategoryList" )
	public ResponseEntity<GenericRes> getAllProjectByCategory() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}

	@GetMapping(value = "/allSalesProjects/{category_id}")
	public ResponseEntity<GenericRes> allSalesProjects(@PathVariable("category_id") int categoryID) throws Exception {
		
		return prepareSuccessResponse(salesManagerService.getallSalesProjects(categoryID));
	}

	@GetMapping(value = "/salesNewProjects")
	public ResponseEntity<GenericRes> getVerifiedProjects() throws Exception {
		return prepareSuccessResponse(salesManagerService.salesNewProjects());
	}
	
	@GetMapping(value = "/getProjectDetails/{project_id}")
	public ResponseEntity<GenericRes> getProjectDetails(@PathVariable("project_id") long projectId)
			throws Exception {
		return prepareSuccessResponse(uploaderService.getProjectListById(projectId));
		//return prepareSuccessResponse(uploaderService.getProjectByProjectId(projectId));
	}
	
	@GetMapping(value = "/getProjectDetailed/{project_id}")
	public ResponseEntity<GenericRes> getProjectDetailed(@PathVariable("project_id") long projectId)
			throws Exception {
		//return prepareSuccessResponse(uploaderService.getProjectListById(projectId));
		return prepareSuccessResponse(uploaderService.getProjectByProjectId(projectId));
	}
	
	
	@PutMapping(value="/addingPrice/{project_id}/{editPrice}")	
	public ResponseEntity<GenericRes> addingPrice(@PathVariable("project_id") long projectId,
			@PathVariable("editPrice") long editPrice ) throws Exception{
		return prepareSuccessResponse(salesManagerService.editPrice(projectId, editPrice));
	}
	/*
	 * @GetMapping(value = "/newProjects") public ResponseEntity<GenericRes>
	 * getNewProjects() throws Exception { return
	 * prepareSuccessResponse(salesManagerService.getNewProjects()); }
	 */

	/*
	 * @GetMapping(value = "/projectCategory") public ResponseEntity<GenericRes>
	 * getprojectsByCategory() throws Exception { return
	 * prepareSuccessResponse(salesManagerService.getprojectsByCategory()); }
	 * 
	 * @GetMapping(value = "/projectCategorybyId/{category}") public
	 * ResponseEntity<GenericRes> projectCategorybyId(@PathVariable("category") int
	 * category) throws Exception { return
	 * prepareSuccessResponse(salesManagerService.getprojectsByCategoryID(category))
	 * ; }
	 */
}
