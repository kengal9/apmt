package com.snipe.apmt.admin.service;

import java.util.List;

import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.model.CategoryModel;


public interface CategoryService {
	
	
	public String addCategory(CategoryModel CategoryModel) throws Exception;

	public List<CategoryDomain> getCategoryList() throws Exception;

	public CategoryDomain getCategoryListById(int categoryId) throws Exception;

	public String updateCategory(CategoryModel CategoryModel) throws Exception;

	public int deleteCategoryById(String categoryId) throws Exception;
 


}
