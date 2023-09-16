package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.model.RoleModel;

@Component
public class RoleMapper extends AbstractModelMapper<RoleModel, RoleDomain> {

	@Override
	public Class<RoleModel> entityType() {
		// TODO Auto-generated method stub
		return RoleModel.class;
	}

	@Override
	public Class<RoleDomain> modelType() {
		// TODO Auto-generated method stub
		return RoleDomain.class;
	}

}
