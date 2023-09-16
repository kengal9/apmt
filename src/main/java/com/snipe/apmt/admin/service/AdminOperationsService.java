package com.snipe.apmt.admin.service;

import java.util.List;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserLogModel;
import com.snipe.apmt.model.UserModel;

public interface AdminOperationsService {

	public List<UserModel> getExternalUsersList() throws Exception;

	public List<UserModel> getInternalUsersList() throws Exception;

	public UserModel updateUserStatus(String id, boolean status);

	public String getIdForUser(UserModel userModel);

	public List<UserLogModel> getActivityDetails(String roleName);

}
