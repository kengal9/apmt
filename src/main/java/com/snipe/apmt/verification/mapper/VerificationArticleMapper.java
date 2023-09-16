package com.snipe.apmt.verification.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.verification.domain.VerificationArticleDomain;
import com.snipe.apmt.verification.model.VerificationArticleModel;


@Component
public class VerificationArticleMapper extends AbstractModelMapper<VerificationArticleModel, VerificationArticleDomain> {
	
	@Override
	public Class<VerificationArticleModel> entityType() {

		return VerificationArticleModel.class;
	}

	@Override
	public Class<VerificationArticleDomain> modelType() {

		return VerificationArticleDomain.class;
	}

}
