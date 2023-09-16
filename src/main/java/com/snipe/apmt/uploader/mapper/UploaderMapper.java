package com.snipe.apmt.uploader.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.model.UploaderModel;

@Component
public class UploaderMapper extends AbstractModelMapper<UploaderModel,UploaderDomain>{

	@Override
	public Class<UploaderModel> entityType() {
		
		return UploaderModel.class;
	}

	@Override
	public Class<UploaderDomain> modelType() {
		
		return UploaderDomain.class;
	}

}
