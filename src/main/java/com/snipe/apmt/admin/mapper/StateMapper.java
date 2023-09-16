package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.admin.domain.StateDomain;
import com.snipe.apmt.admin.model.StateModel;

@Component
public class StateMapper extends AbstractModelMapper<StateModel, StateDomain> {

	@Override
	public Class<StateModel> entityType() {
		// TODO Auto-generated method stub
		return StateModel.class;
	}

	@Override
	public Class<StateDomain> modelType() {
		// TODO Auto-generated method stub
		return StateDomain.class;
	}

}
