package com.snipe.apmt.service;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;

public interface UserCommonService {

	UserModel save(UserModel userModel, UserDomain userDomain) throws Exception;

	String update(UserModel userModel) throws Exception;

}
