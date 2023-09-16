package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import java.math.BigInteger;

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
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.BankService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class BankController {

	@Autowired
	BankService bankService;

	@PostMapping(value = "/save/bank")
	public ResponseEntity<GenericRes> saveBankInfo(@RequestBody UserModel userModel) throws Exception {
		return prepareSuccessResponse(bankService.saveBankInfo(userModel));
	}

	@DeleteMapping(value = "/delete/bank/{bankId}")
	public ResponseEntity<GenericRes> deletBankById(@PathVariable("bankId") String bankId) throws Exception {
		return prepareSuccessResponse(bankService.deleteBankById(bankId));
	}

	@GetMapping(value = "get/bank/{bankId}")
	public ResponseEntity<GenericRes> getBankById(@PathVariable("bankId") String bankId) throws Exception {
		return prepareSuccessResponse(bankService.getBankById(bankId));
	}

	@GetMapping(value = "get/bankByUserId/{}")
	public ResponseEntity<GenericRes> getBankByUserId(@PathVariable("userId") String userId) throws Exception {
		return prepareSuccessResponse(bankService.getBankByUserId(userId));
	}

	@GetMapping(value = "get/bankByAccountNumber/{accountNumber}")
	public ResponseEntity<GenericRes> getBankByAccountNumber(@PathVariable("accountNumber") BigInteger accountNumber)
			throws Exception {
		return prepareSuccessResponse(bankService.isAccountNumberExists(accountNumber));
	}
	
	

}
