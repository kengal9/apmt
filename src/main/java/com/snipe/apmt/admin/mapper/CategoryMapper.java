package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.model.CategoryModel;
import com.snipe.apmt.mapper.AbstractModelMapper;

@Component
public class CategoryMapper extends AbstractModelMapper<CategoryModel, CategoryDomain> {
	@Override
	public Class<CategoryModel> entityType() {
		// TODO Auto-generated method stub
		return CategoryModel.class;
	}

	@Override
	public Class<CategoryDomain> modelType() {
		// TODO Auto-generated method stub
		return CategoryDomain.class;
	}

}
