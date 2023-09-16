package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.domain.EmployeeRoleDomain;
import com.snipe.apmt.admin.model.EmployeeRoleModel;
import com.snipe.apmt.mapper.AbstractModelMapper;

@Component
public class EmployeeRoleMapper extends AbstractModelMapper<EmployeeRoleModel, EmployeeRoleDomain>{

	@Override
	public Class<EmployeeRoleModel> entityType() {
		// TODO Auto-generated method stub
		return EmployeeRoleModel.class;
	}

	@Override
	public Class<EmployeeRoleDomain> modelType() {
		// TODO Auto-generated method stub
		return EmployeeRoleDomain.class;
	}

}
