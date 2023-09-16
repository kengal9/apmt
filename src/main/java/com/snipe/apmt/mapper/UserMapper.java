package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;

@Component
public class UserMapper extends AbstractModelMapper<UserModel, UserDomain> {

	@Override
	public Class<UserModel> entityType() {
		// TODO Auto-generated method stub
		return UserModel.class;
	}

	@Override
	public Class<UserDomain> modelType() {
		// TODO Auto-generated method stub
		return UserDomain.class;
	}

}
