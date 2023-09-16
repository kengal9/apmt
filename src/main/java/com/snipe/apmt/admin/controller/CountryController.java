package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.admin.service.CountryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")
public class CountryController {

	@Autowired
	CountryService countryService;

	@PostMapping(value = "/addCountry")
	@ResponseBody
	public ResponseEntity<GenericRes> addCountry(@RequestBody CountryModel countryModel) throws Exception {
		return prepareSuccessResponse(countryService.addCountry(countryModel));
	}

	@GetMapping(value = "/countryList")
	public ResponseEntity<GenericRes> getCountryList() throws Exception {
		return prepareSuccessResponse(countryService.getCountryList());
	}

	@GetMapping(value = "/countryList/{countryId}")
	public ResponseEntity<GenericRes> getCountryListById(@PathVariable("countryId") String countryID) throws Exception {
		return prepareSuccessResponse(countryService.getCountryListById(countryID));
	}

	@PutMapping(value = "/updateCountry")
	public ResponseEntity<GenericRes> updateCountry(@RequestBody CountryModel countryModel) throws Exception {
		return prepareSuccessResponse(countryService.updateCountry(countryModel));

	}

	@DeleteMapping(value = "/deleteCountry/{countryId}")
	public ResponseEntity<GenericRes> deleteCountryById(@PathVariable("countryId") String countryId) throws Exception {
		return prepareSuccessResponse(countryService.deleteCountryById(countryId));
	}
}
