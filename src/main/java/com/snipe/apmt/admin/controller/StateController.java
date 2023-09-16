package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.admin.model.StateModel;
import com.snipe.apmt.admin.service.StateService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")
public class StateController {

	@Autowired
	StateService stateService;

	@PostMapping(value = "/addState")
	public ResponseEntity<GenericRes> addState(@RequestBody StateModel stateModel) throws Exception {
		return prepareSuccessResponse(stateService.addState(stateModel));

	}

	@GetMapping(value = "/stateList")
	public ResponseEntity<GenericRes> getStateList() throws Exception {
		return prepareSuccessResponse(stateService.getStateList());
	}

	@GetMapping(value = "/state/Details/{stateId}")
	public ResponseEntity<GenericRes> getStateListById(@PathVariable("stateId") String stateId) throws Exception {
		return prepareSuccessResponse(stateService.getStateListById(stateId));
	}

	@GetMapping(value = "/state/List/{countryId}")
	public ResponseEntity<GenericRes> getStatedetails(@PathVariable("countryId") String countryId) throws Exception {
		return prepareSuccessResponse(stateService.getStatedetails(countryId));
	}

	@PutMapping(value = "/updateState")
	public ResponseEntity<GenericRes> updateState(@RequestBody StateModel stateModel) throws Exception {
		return prepareSuccessResponse(stateService.updateState(stateModel));

	}

	@DeleteMapping(value = "/deleteState/{stateId}")
	public ResponseEntity<GenericRes> deleteByStateId(@PathVariable("stateId") String stateId) throws Exception {
		return prepareSuccessResponse(stateService.deleteStateById(stateId));
	}

	

	@GetMapping(value = "/state/count")
	public ResponseEntity<GenericRes> stateCount() throws Exception {
		return prepareSuccessResponse(stateService.stateCount());
	}

}
