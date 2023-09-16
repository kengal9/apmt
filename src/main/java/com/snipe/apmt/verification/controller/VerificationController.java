
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
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.service.UploaderService;
import com.snipe.apmt.verification.model.VerificationModel;
import com.snipe.apmt.verification.service.IVerificationService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")

public class VerificationController {

	@Autowired
	IVerificationService verificationService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UploaderService uploaderService;

	@GetMapping(value = "/getAllProjects")
	public ResponseEntity<GenericRes> getAllProjects() throws Exception {
		return prepareSuccessResponse(uploaderService.getProjectList());
	}

	@GetMapping(value = "/getVerifiedProjects")
	public ResponseEntity<GenericRes> getVerifiedProjects() throws Exception {
		return prepareSuccessResponse(verificationService.getVerifiedProjects());
	}

	@GetMapping(value = "/getNewProjects")
	public ResponseEntity<GenericRes> getNewProjects() throws Exception {
		return prepareSuccessResponse(verificationService.getNewProjects());
	}

	@GetMapping(value = "/getProjectCategories")
	public ResponseEntity<GenericRes> getProjectCategories() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList());
	}

	@GetMapping(value = "/getProjectsByCategoryId/{category_id}")
	public ResponseEntity<GenericRes> getProjectsByCategoryId(@PathVariable("category_id") int categoryID)
			throws Exception {
		return prepareSuccessResponse(verificationService.getProjectsListByCategoryId(categoryID));
	}

	@GetMapping(value = "/getProjectDetailsById/{project_id}")
	public ResponseEntity<GenericRes> getProjectDetailsById(@PathVariable("project_id") long projectId)
			throws Exception {
		return prepareSuccessResponse(uploaderService.getProjectByProjectId(projectId));
	}

//	@PutMapping(value = "/verifyProject/{project_id}/{verifiedStatus}")
//	public ResponseEntity<GenericRes> verifyProject(@PathVariable("project_id") long projectId,
//			@PathVariable("verifiedStatus") boolean verifiedStatus) throws Exception {
//		return prepareSuccessResponse(verificationService.verifyProject(projectId, verifiedStatus));
//	}
	
	@PostMapping(value = "/verifyProject")
	public ResponseEntity<GenericRes> verifyProject(@RequestBody VerificationModel verificationModel) throws Exception {
		return prepareSuccessResponse(verificationService.verifyProject(verificationModel));
	}

	@PutMapping(value = "/approveProject/{project_id}/{approveStatus}")
	public ResponseEntity<GenericRes> approveProject(@PathVariable("project_id") long projectId,
			@PathVariable("approveStatus") boolean approveStatus) throws Exception {
		return prepareSuccessResponse(verificationService.approveProject(projectId, approveStatus));
	}

	@PutMapping(value = "/rejectProject/{project_id}/{rejectStatus}")
	public ResponseEntity<GenericRes> rejectProject(@PathVariable("project_id") long projectId,
			@PathVariable("rejectStatus") boolean rejectStatus) throws Exception {
		return prepareSuccessResponse(verificationService.rejectProject(projectId, rejectStatus));
	}

	@GetMapping(value = "/getVerificationDetails")
	public ResponseEntity<GenericRes> getVerificationDetails() throws Exception {
		return prepareSuccessResponse(verificationService.getVerificationDetails());
	}

	@GetMapping(value = "/getVerificationDetailsByID/{project_id}")
	public ResponseEntity<GenericRes> getVerificationDetailsByID(@PathVariable("project_id") long projectId)
			throws Exception {
		return prepareSuccessResponse(verificationService.getVerificationDetailsByID(projectId));
	}

}
