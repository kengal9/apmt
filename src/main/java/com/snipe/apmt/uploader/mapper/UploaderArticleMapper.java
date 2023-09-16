package com.snipe.apmt.uploader.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.model.UploaderArticleModel;

@Component
public class UploaderArticleMapper extends AbstractModelMapper<UploaderArticleModel, UploaderArticleDomain> {

	@Override
	public Class<UploaderArticleModel> entityType() {
		return UploaderArticleModel.class;
	}

	@Override
	public Class<UploaderArticleDomain> modelType() {
		return UploaderArticleDomain.class;
	}

	
}
