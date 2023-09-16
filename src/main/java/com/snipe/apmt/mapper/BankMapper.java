package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.model.BankModel;

@Component
public class BankMapper extends AbstractModelMapper<BankModel, BankDomain> {

	@Override
	public Class<BankModel> entityType() {
		// TODO Auto-generated method stub
		return BankModel.class;
	}

	@Override
	public Class<BankDomain> modelType() {
		// TODO Auto-generated method stub
		return BankDomain.class;
	}

}
