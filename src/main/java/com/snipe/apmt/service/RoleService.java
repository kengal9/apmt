package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.model.RoleModel;

public interface RoleService {

	public String addRole(RoleModel roleModel) throws Exception;

	public RoleModel getRole(int roleId) throws Exception;

	public RoleModel roleCount() throws Exception;

	public List<RoleModel> getRoleList() throws Exception;

	public String updateRole(RoleModel roleModel) throws Exception;

	public int deleteRoleById(int roleId) throws Exception;

}
