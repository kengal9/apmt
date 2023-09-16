package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.model.BusinessRoleModel;
import com.snipe.apmt.admin.model.EmployeeRoleModel;
import com.snipe.apmt.admin.service.BusinessRoleService;
import com.snipe.apmt.admin.service.EmployeeRoleService;
import com.snipe.apmt.exception.GenericRes;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings("rawtypes")
public class BusinessRoleController {
	
	@Autowired
	BusinessRoleService businessRoleService;
	
	@PostMapping(value = "/addBusinessRole")
	@ResponseBody
	public ResponseEntity<GenericRes> addBusinessRole(@RequestBody BusinessRoleModel businessRoleModel) throws Exception {
		return prepareSuccessResponse(businessRoleService.addBusinessRole(businessRoleModel));
	}

	@GetMapping(value = "/businessRoleList")
	public ResponseEntity<GenericRes> getBusinessRoleList() throws Exception {
		return prepareSuccessResponse(businessRoleService.getBusinessRoleList());
	}
	
	@GetMapping(value = "/businessRoleList/{businessRoleId}")
	public ResponseEntity<GenericRes> getBusinessListById(@PathVariable("businessRoleId") String businessRoleID) throws Exception {
		return prepareSuccessResponse(businessRoleService.getBusinessRoleListById(businessRoleID));
	}
	
	@PutMapping(value = "/updateBusinessRole")
	public ResponseEntity<GenericRes> updateBusiness(@RequestBody BusinessRoleModel businessRoleModel) throws Exception {
		return prepareSuccessResponse(businessRoleService.updateBusinessRole(businessRoleModel));
	}
	
	@DeleteMapping(value = "/deleteBusinessRole/{businessRoleId}")
	public ResponseEntity<GenericRes> deleteBusinessRoleById(@PathVariable("businessRoleId") String businessRoleId) throws Exception {
			return prepareSuccessResponse(businessRoleService.deleteBusinessRoleById(businessRoleId));
		}
}
