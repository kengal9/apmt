package com.snipe.apmt.verification.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.verification.domain.VerificationBookDomain;
import com.snipe.apmt.verification.model.VerificationBookModel;


@Component
public class VerificationBookMapper extends AbstractModelMapper<VerificationBookModel, VerificationBookDomain> {

	@Override
	public Class<VerificationBookModel> entityType() {
		
		return VerificationBookModel.class;
	}

	@Override
	public Class<VerificationBookDomain> modelType() {
		
		return VerificationBookDomain.class;
	}


}
