package com.snipe.apmt.admin.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.admin.model.CityModel;
import com.snipe.apmt.admin.service.CityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")
public class CityController {

	@Autowired
	CityService cityService;

	@PostMapping(value = "/city")
	public ResponseEntity<GenericRes> addCity(@RequestBody CityModel cityModel) throws Exception {
		return prepareSuccessResponse(cityService.addCity(cityModel));
	}

	@GetMapping(value = "/cityList")
	public ResponseEntity<GenericRes> getCityList() throws Exception {
		return prepareSuccessResponse(cityService.getCityList());
	}

	@GetMapping(value = "/city/list/{stateId}")
	public ResponseEntity<GenericRes> getCityList(@PathVariable("stateId") String stateId) throws Exception {
		return prepareSuccessResponse(cityService.getCityList(stateId));
	}

	@GetMapping(value = "/cityList/{cityID}")
	public ResponseEntity<GenericRes> getCityListById(@PathVariable("cityID") String cityID) throws Exception {
		return prepareSuccessResponse(cityService.getCityListById(cityID));
	}

	@PutMapping(value = "/updateCity")
	public ResponseEntity<GenericRes> updateCity(@RequestBody CityModel cityModel) throws Exception {
		return prepareSuccessResponse(cityService.updateCity(cityModel));

	}

	@DeleteMapping(value = "/deleteCity/{cityId}")
	public ResponseEntity<GenericRes> deleteCityById(@PathVariable("cityId") String cityId) throws Exception {
		return prepareSuccessResponse(cityService.deleteCityById(cityId));
	}
}
