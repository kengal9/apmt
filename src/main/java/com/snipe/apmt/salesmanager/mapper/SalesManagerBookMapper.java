package com.snipe.apmt.salesmanager.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.salesmanager.domain.SalesManagerBookDomain;
import com.snipe.apmt.salesmanager.model.SalesManagerBookModel;

@Component
public class SalesManagerBookMapper extends AbstractModelMapper<SalesManagerBookModel, SalesManagerBookDomain>{

	@Override
	public Class<SalesManagerBookModel> entityType() {
		return SalesManagerBookModel.class;
	}

	@Override
	public Class<SalesManagerBookDomain> modelType() {
		return SalesManagerBookDomain.class;
	}

}
