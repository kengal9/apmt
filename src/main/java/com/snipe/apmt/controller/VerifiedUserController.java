package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.service.VerifiedUserService;

@RestController
@RequestMapping("/v1")
public class VerifiedUserController {

	@Autowired
	VerifiedUserService verifiedUserService;
	
	@GetMapping(value = "/verifiedUsersList")
	public ResponseEntity<GenericRes> verfiedUsersList() throws Exception {
		return prepareSuccessResponse(verifiedUserService.verifiedUsersList());
	}
	
	@GetMapping(value = "/saveVerifiedUser")
	public ResponseEntity<GenericRes> saveVerifiedUser(@PathVariable("userId") String userId) throws Exception {
		return prepareSuccessResponse(verifiedUserService.saveVerifiedUser(userId));
	}
	
	@GetMapping(value = "/checkUserVerified/{userId}")
	public ResponseEntity<GenericRes> checkUserVerified(@PathVariable("userId") String userId) throws Exception {
		return prepareSuccessResponse(verifiedUserService.checkUserVerified(userId));
	}
}
