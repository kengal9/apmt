package com.snipe.apmt.admin.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.admin.domain.AdminBankDomain;
import com.snipe.apmt.admin.model.AdminBankModel;

@Component
public class AdminBankMapper extends AbstractModelMapper<AdminBankModel, AdminBankDomain> {

	@Override
	public Class<AdminBankModel> entityType() {
		// TODO Auto-generated method stub
		return AdminBankModel.class;
	}

	@Override
	public Class<AdminBankDomain> modelType() {
		// TODO Auto-generated method stub
		return AdminBankDomain.class;
	}

}
