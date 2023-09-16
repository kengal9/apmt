package com.snipe.apmt.admin.service;

import java.util.List;

import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.admin.model.EmployeeRoleModel;

public interface EmployeeRoleService {

	public String addEmployeeRole(EmployeeRoleModel employeeRoleModel) throws Exception;

	public List<EmployeeRoleModel> getEmployeeRoleList() throws Exception;

	public EmployeeRoleModel getEmployeeRoleListById(String employeeRoleID) throws Exception;

	public String updateEmployeeRole(EmployeeRoleModel employeeRoleModel) throws Exception;
	
	public int deleteEmployeeRoleById(String employeeRoleId) throws Exception;

}
