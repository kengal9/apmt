package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.service.AdminOperationsService;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.model.UserModel;

@RestController
@RequestMapping("/v1/admin")
@SuppressWarnings("rawtypes")

public class AdminOperationsController {
	private static Logger log = LoggerFactory.getLogger(AdminOperationsController.class);

	@Autowired
	AdminOperationsService adminOperService;

	@GetMapping(value = "/externalUsers")
	public ResponseEntity<GenericRes> getListOfExternalUsers() {
		try {
			return prepareSuccessResponse(adminOperService.getExternalUsersList());
		} catch (Exception e) {
			log.error("Exception in fetching List of external users:", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@GetMapping(value = "/internalUsers")
	public ResponseEntity<GenericRes> getListOfInternalUsers(){
		try {
			return prepareSuccessResponse(adminOperService.getInternalUsersList());
		} catch (Exception e) {
			log.error("Exception in fetching List of internal users:", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@PutMapping(value = "/updateStatus/{id}/{status}")
	public ResponseEntity<GenericRes> updateUserStatus(@PathVariable("id") String id,
			@PathVariable("status") boolean status) {
		try {
			return prepareSuccessResponse(adminOperService.updateUserStatus(id, status));
		} catch (Exception e) {
			log.error("Exception in updating status of user:", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@GetMapping(value = "/getId")
	public ResponseEntity<GenericRes> getIdForUser(@RequestBody UserModel userModel) throws Exception {
		
		return prepareSuccessResponse(adminOperService.getIdForUser(userModel));
	}

	@GetMapping(value = "/getActivityDetailsForVM")
	public ResponseEntity<GenericRes> getActivityDetailsForVerificationMgr() throws Exception {
		try {
			return prepareSuccessResponse(adminOperService.getActivityDetails("verificationManager"));
		} catch (Exception e) {
			log.error("Exception in getActivityDetailsForVM :", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		
	}
	@GetMapping(value = "/getActivityDetailsForSM")
	public ResponseEntity<GenericRes> getActivityDetailsForSalesMgr() throws Exception {
		try {
			return prepareSuccessResponse(adminOperService.getActivityDetails("salesManager"));
		} catch (Exception e) {
			log.error("Exception in getActivityDetailsForSM:", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		
	}
}
