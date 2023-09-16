package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.domain.BusinessRoleDomain;
import com.snipe.apmt.admin.domain.EmployeeRoleDomain;
import com.snipe.apmt.admin.model.BusinessRoleModel;
import com.snipe.apmt.admin.model.EmployeeRoleModel;
import com.snipe.apmt.mapper.AbstractModelMapper;

@Component
public class BusinessRoleMapper extends AbstractModelMapper<BusinessRoleModel, BusinessRoleDomain> {

	@Override
	public Class<BusinessRoleModel> entityType() {
		// TODO Auto-generated method stub
		return BusinessRoleModel.class;
	}

	@Override
	public Class<BusinessRoleDomain> modelType() {
		// TODO Auto-generated method stub
		return BusinessRoleDomain.class;
	}

}
