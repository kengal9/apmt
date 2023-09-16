package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.GenderModel;
import com.snipe.apmt.model.ProjectModel;
import com.snipe.apmt.service.GenderService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class GenderController {
	
	@Autowired
	GenderService genderService;
	
	@PostMapping(value = "/save/Gender")
	public ResponseEntity<GenericRes> saveGender(@RequestBody GenderModel genderModel) throws Exception {
		return prepareSuccessResponse(genderService.saveGender(genderModel));
	}
	
	@GetMapping(value = "/genderList")
	public ResponseEntity<GenericRes> getGenderList() throws Exception {
		return prepareSuccessResponse(genderService.getGenderList());
	}
	
	@GetMapping(value = "/gender/{genderId}")
	public ResponseEntity<GenericRes> getProjectListById(@PathVariable("genderId") String genderId) throws Exception {
		return prepareSuccessResponse(genderService.getGenderById(genderId));
	}
	
	@PutMapping(value = "/updateGender")
	public ResponseEntity<GenericRes> updateGender(@RequestBody GenderModel genderModel) throws Exception {
		return prepareSuccessResponse(genderService.updateGender(genderModel));

	}
	
	@DeleteMapping(value = "/deleteGender/{genderId}")
	public ResponseEntity<GenericRes> deleteByGenderId(@PathVariable("genderId") String genderId) throws Exception {
		return prepareSuccessResponse(genderService.deleteByGenderId(genderId));
	}

}
