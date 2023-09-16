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
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.admin.model.CategoryModel;
import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.exception.GenericRes;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1")

@SuppressWarnings("rawtypes")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(value="/category")
	public ResponseEntity<GenericRes> addCategory(@RequestBody CategoryModel categoryModel) throws Exception {
		return prepareSuccessResponse(categoryService.addCategory(categoryModel));
	}
	
	@GetMapping(value = "/categoryList")
	public ResponseEntity<GenericRes> getCategoryList() throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryList()); 
	}

	@GetMapping(value = "/getcategoryListById/{category_id}")
	public ResponseEntity<GenericRes> getcategoryListById(@PathVariable("category_id") int categoryID) throws Exception {
		return prepareSuccessResponse(categoryService.getCategoryListById(categoryID));
	}

	@PutMapping(value = "/updateCategory")
	public ResponseEntity<GenericRes> updateCategory(@RequestBody CategoryModel categoryModel) throws Exception {
		return prepareSuccessResponse(categoryService.updateCategory(categoryModel));

	}

	@DeleteMapping(value = "/deleteCategory/{categoryId}")
	public ResponseEntity<GenericRes> deleteCityById(@PathVariable("categoryId") String categoryId) throws Exception {
		return prepareSuccessResponse(categoryService.deleteCategoryById(categoryId));
	}
	

}
