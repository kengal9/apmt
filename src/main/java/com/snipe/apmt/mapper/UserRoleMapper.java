package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.UserRoleDomain;
import com.snipe.apmt.model.UserRoleModel;

@Component
public class UserRoleMapper extends AbstractModelMapper<UserRoleModel, UserRoleDomain> {

	@Override
	public Class<UserRoleModel> entityType() {
		return UserRoleModel.class;
	}

	@Override
	public Class<UserRoleDomain> modelType() {
		return UserRoleDomain.class;
	}

}
