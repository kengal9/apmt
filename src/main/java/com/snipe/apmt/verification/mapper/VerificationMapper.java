package com.snipe.apmt.verification.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.verification.domain.VerificationDomain;
import com.snipe.apmt.verification.model.VerificationModel;

@Component
public class VerificationMapper extends AbstractModelMapper<VerificationModel, VerificationDomain> {

	@Override
	public Class<VerificationModel> entityType() {

		return VerificationModel.class;
	}

	@Override
	public Class<VerificationDomain> modelType() {

		return VerificationDomain.class;
	}

}
