package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.model.GenderModel;


@Component
public class GenderMapper extends AbstractModelMapper<GenderModel, GenderDomain> {

	@Override
	public Class<GenderModel> entityType() {
		// TODO Auto-generated method stub
		return GenderModel.class;
	}

	@Override
	public Class<GenderDomain> modelType() {
		// TODO Auto-generated method stub
		return GenderDomain.class;
	}

}
