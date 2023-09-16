package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;


import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.admin.model.CityModel;
import com.snipe.apmt.mapper.AbstractModelMapper;

@Component
public class CityMapper extends AbstractModelMapper<CityModel, CityDomain> {

	@Override
	public Class<CityModel> entityType() {
		// TODO Auto-generated method stub
		return CityModel.class;
	}

	@Override
	public Class<CityDomain> modelType() {
		// TODO Auto-generated method stub
		return CityDomain.class;
	}

}
