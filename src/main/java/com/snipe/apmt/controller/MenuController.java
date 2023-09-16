package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.GenderModel;
import com.snipe.apmt.model.MenuModel;
import com.snipe.apmt.service.MenuService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class MenuController {

	@Autowired
	MenuService menuService;
	
//	@Autowired
//	MenuModel menuModel;
//	
	@GetMapping(value = "/menu/{roleId}")
	public ResponseEntity<GenericRes> getRoleNameBasedProfile(@PathVariable("roleId") int roleId) throws Exception {
		return prepareSuccessResponse(menuService.getMenus(roleId));
	}
	
	@PostMapping(value = "/save/Menu")
	public ResponseEntity<GenericRes> saveMenu(@RequestBody MenuModel menuModel) throws Exception {
		return prepareSuccessResponse(menuService.saveMenu(menuModel));
	}
}
