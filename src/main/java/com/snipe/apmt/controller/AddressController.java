package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.AddressService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class AddressController {

	@Autowired
	AddressService addressService;

	@PostMapping(value = "/save/Address")
	public ResponseEntity<GenericRes> saveAddressInfo(@RequestBody UserModel userModel) throws Exception {
		return prepareSuccessResponse(addressService.saveAddressInfo(userModel));
	}

	@DeleteMapping(value = "/delete/Address/{addressId}")
	public ResponseEntity<GenericRes> deleteAddressById(@PathVariable("addressId") String addressId) throws Exception {
		return prepareSuccessResponse(addressService.deleteAddressById(addressId));
	}

	@GetMapping(value = "/addressByStateId/inActive/{stateId}/{roleId}")
	public ResponseEntity<GenericRes> getAddressByStateId(@PathVariable("stateId") String stateId,
			@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(addressService.getAddressByStateId(stateId, roleId, false));
	}

	@GetMapping(value = "/addressByStateId/Active/{stateId}/{roleId}")
	public ResponseEntity<GenericRes> getInactiveAddressByStateId(@PathVariable("stateId") String stateId,
			@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(addressService.getAddressByStateId(stateId, roleId, true));
	}

	@GetMapping(value = "/addressByCityId/inActive/{cityId}/{roleId}")
	public ResponseEntity<GenericRes> getActiveAddressByCityId(@PathVariable("cityId") String cityId,
			@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(addressService.getAddressByCityId(cityId, roleId, false));
	}

	@GetMapping(value = "/addressByCityId/Active/{cityId}/{roleId}")
	public ResponseEntity<GenericRes> getInactiveAddressByCityId(@PathVariable("cityId") String cityId,
			@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(addressService.getAddressByCityId(cityId, roleId, true));
	}

	@GetMapping(value = "/addressByUserId/{userId}/{roleId}")
	public ResponseEntity<GenericRes> getAddressByUserId(@PathVariable("userId") String userId,
			@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(addressService.getAddressByUserId(userId, roleId));
	}

}
