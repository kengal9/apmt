package com.snipe.apmt.admin.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.CategoryRepository;
import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.mapper.CategoryMapper;
import com.snipe.apmt.admin.model.CategoryModel;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public String addCategory(CategoryModel CategoryModel) throws Exception {
		CategoryDomain categoryDomain=new CategoryDomain();
		BeanUtils.copyProperties(CategoryModel, categoryDomain);
		categoryRepository.save(categoryDomain);
		return "Category Saved";
	
	}

	@Override
	public List<CategoryDomain> getCategoryList() throws Exception {
		List<CategoryDomain> categoryDomian=categoryRepository.findAll();
		return categoryDomian;
		
	}

	@Override
	public CategoryDomain getCategoryListById(int categoryId) throws Exception {
		
		CategoryDomain categoryDomain=categoryRepository.findByCategoryId(categoryId);
		return categoryDomain;
	
	}

	@Override
	public String updateCategory(CategoryModel categoryModel) throws Exception {
		CategoryDomain categoryDomain= new CategoryDomain();
		BeanUtils.copyProperties(categoryModel,categoryDomain);
		categoryRepository.save(categoryDomain);
		return "Category Update";
		
		
	}

	@Override
	public int deleteCategoryById(String categoryId) throws Exception {
		return categoryRepository.deleteByCategoryId(categoryId);
	}

	

}
