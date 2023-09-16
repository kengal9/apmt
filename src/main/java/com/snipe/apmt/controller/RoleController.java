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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.RoleModel;
import com.snipe.apmt.service.RoleService;

@RestController
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping(value = "/addRole")
	public ResponseEntity<GenericRes> addRole(@RequestBody RoleModel roleModel) throws Exception {
		return prepareSuccessResponse(roleService.addRole(roleModel));
	}

	@GetMapping(value = "/role/{roleId}")
	public ResponseEntity<GenericRes> getRole(@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(roleService.getRole(roleId));
	}

	@GetMapping(value = "/roleCount")
	public ResponseEntity<GenericRes> roleCount() throws Exception {
		return prepareSuccessResponse(roleService.roleCount());
	}

	@GetMapping(value = "/roleList")
	public ResponseEntity<GenericRes> getRoleList() throws Exception {
		return prepareSuccessResponse(roleService.getRoleList());
	}

	@PutMapping(value = "/updateRole")
	public ResponseEntity<GenericRes> updateRole(@RequestBody RoleModel roleModel) throws Exception {
		return prepareSuccessResponse(roleService.updateRole(roleModel));
	}

	@DeleteMapping(value = "/deleteRole/{roleId}")
	public ResponseEntity<GenericRes> deleteRoleById(@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(roleService.deleteRoleById(roleId));
	}

}
