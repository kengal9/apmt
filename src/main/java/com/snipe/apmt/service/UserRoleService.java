package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.domain.UserRoleDomain;
import com.snipe.apmt.model.UserRoleModel;

public interface UserRoleService {

	public String addUserRole(UserRoleModel userRoleModel) throws Exception;

	public List<UserRoleDomain> getUserRoleByUserID(String userId) throws Exception;

	public UserRoleModel userRoleCount(String userId) throws Exception;

	public List<UserRoleModel> getUserRoleList() throws Exception;

	public String updateUserRole(UserRoleModel userRoleModel) throws Exception;

	public String deleteUserRoleById(String uuId) throws Exception;

}
