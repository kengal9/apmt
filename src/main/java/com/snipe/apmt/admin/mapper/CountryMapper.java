package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.model.CountryModel;

@Component
public class CountryMapper extends AbstractModelMapper<CountryModel, CountryDomain> {

	@Override
	public Class<CountryModel> entityType() {
		// TODO Auto-generated method stub
		return CountryModel.class;
	}

	@Override
	public Class<CountryDomain> modelType() {
		// TODO Auto-generated method stub
		return CountryDomain.class;
	}

}
