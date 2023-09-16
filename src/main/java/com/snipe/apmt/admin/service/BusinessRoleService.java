package com.snipe.apmt.admin.service;

import java.util.List;

import com.snipe.apmt.admin.model.BusinessRoleModel;

public interface BusinessRoleService {

	public String addBusinessRole(BusinessRoleModel businessRoleModel) throws Exception;

	public List<BusinessRoleModel> getBusinessRoleList() throws Exception;

	public BusinessRoleModel getBusinessRoleListById(String businessRoleID) throws Exception;

	public String updateBusinessRole(BusinessRoleModel businessRoleModel) throws Exception;
	
	public int deleteBusinessRoleById(String businessRoleId) throws Exception;

}
