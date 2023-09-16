package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.UserRoleModel;
import com.snipe.apmt.service.UserRoleService;

@RestController
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")

public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;

	@PostMapping(value = "/addUserRole")
	public ResponseEntity<GenericRes> addUserRole(@RequestBody UserRoleModel userRoleModel) throws Exception {
		return prepareSuccessResponse(userRoleService.addUserRole(userRoleModel));
	}

	@GetMapping(value = "/userRole/{userId}")
	public ResponseEntity<GenericRes> getUserRoleByProfileID(@PathVariable("userId") String userId) throws Exception {
		return prepareSuccessResponse(userRoleService.getUserRoleByUserID(userId));
	}

	@DeleteMapping(value = "/deleteUserRole/{uuId}")
	public ResponseEntity<GenericRes> deleteUserRoleById(@PathVariable("uuId") String uuId) throws Exception {
		return prepareSuccessResponse(userRoleService.deleteUserRoleById(uuId));
	}

	@GetMapping(value = "/userRoleCount/{userId}")
	public ResponseEntity<GenericRes> userRoleCount(@PathVariable("userId") String userId) throws Exception {
		return prepareSuccessResponse(userRoleService.userRoleCount(userId));
	}
}
