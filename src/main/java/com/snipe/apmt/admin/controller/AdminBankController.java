package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.model.AdminBankModel;
import com.snipe.apmt.admin.service.AdminBankServiceImpl;
import com.snipe.apmt.exception.GenericRes;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
@SuppressWarnings("rawtypes")
public class AdminBankController {
	
	

	@Autowired
	AdminBankServiceImpl bankService;

	@PostMapping(value = "/admin/addBank")
	public ResponseEntity<GenericRes> adminAddBank(@RequestBody AdminBankModel adminBankModel) throws Exception {
		return prepareSuccessResponse(bankService.adminAddBank(adminBankModel));
	}

	@GetMapping(value = "/admin/bankList")
	public ResponseEntity<GenericRes> getAdminBankList() throws Exception {
		return prepareSuccessResponse(bankService.getAdminBankList());
	}

	@GetMapping(value = "/admin/bankListById/{adminBankId}")
	public ResponseEntity<GenericRes> getAdminBankListById(@PathVariable("adminBankId") String adminBankId)
			throws Exception {
		return prepareSuccessResponse(bankService.getAdminBankListById(adminBankId));
	}
	
//	@GetMapping(value = "/admin/bankListByName/{adminBankName}")
//	public ResponseEntity<GenericRes> getAdminBankListByName(@PathVariable("adminBankname") String adminBankName)
//			throws Exception {
//		return prepareSuccessResponse(bankService.getAdminBankListByname(adminBankName));
//	}

	@PutMapping(value = "/admin/updateBank")
	public ResponseEntity<GenericRes> adminUpdateBank(@RequestBody AdminBankModel adminBankModel) throws Exception {
		return prepareSuccessResponse(bankService.adminUpdateBank(adminBankModel));
	}

	@DeleteMapping(value = "/admin/deleteBank/{adminBankId}")
	public ResponseEntity<GenericRes> adminDeleteBankById(@PathVariable("adminBankId") String adminBankId) throws Exception {
		return prepareSuccessResponse(bankService.adminDeleteBankById(adminBankId));
	}

	@GetMapping(value = "/admin/bankCount")
	public ResponseEntity<GenericRes> adminBankCount() throws Exception {
		return prepareSuccessResponse(bankService.adminBankCount());
	}
}
