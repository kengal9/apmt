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

import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.admin.model.EmployeeRoleModel;
import com.snipe.apmt.admin.service.EmployeeRoleService;
import com.snipe.apmt.exception.GenericRes;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings("rawtypes")
public class EmployeeRoleController {
	
	@Autowired
	EmployeeRoleService employeeRoleService;
	
	@PostMapping(value = "/addEmployeeRole")
	@ResponseBody
	public ResponseEntity<GenericRes> addEmployeeRole(@RequestBody EmployeeRoleModel employeeRoleModel) throws Exception {
		return prepareSuccessResponse(employeeRoleService.addEmployeeRole(employeeRoleModel));
	}

	@GetMapping(value = "/employeeRoleList")
	public ResponseEntity<GenericRes> getEmployeeRoleList() throws Exception {
		return prepareSuccessResponse(employeeRoleService.getEmployeeRoleList());
	}
	
	@GetMapping(value = "/employeeRoleList/{employeeRoleId}")
	public ResponseEntity<GenericRes> getEmployeeListById(@PathVariable("employeeRoleId") String employeeRoleID) throws Exception {
		return prepareSuccessResponse(employeeRoleService.getEmployeeRoleListById(employeeRoleID));
	}
	
	@PutMapping(value = "/updateEmployeeRole")
	public ResponseEntity<GenericRes> updateEmployee(@RequestBody EmployeeRoleModel employeeRoleModel) throws Exception {
		return prepareSuccessResponse(employeeRoleService.updateEmployeeRole(employeeRoleModel));
	}
	
	@DeleteMapping(value = "/deleteEmployeeRole/{employeeRoleId}")
	public ResponseEntity<GenericRes> deleteEmployeeRoleById(@PathVariable("employeeRoleId") String employeeRoleId) throws Exception {
			return prepareSuccessResponse(employeeRoleService.deleteEmployeeRoleById(employeeRoleId));
		}
	
}
