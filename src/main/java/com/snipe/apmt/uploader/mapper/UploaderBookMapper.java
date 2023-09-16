package com.snipe.apmt.uploader.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.model.UploaderBookModel;

@Component
public class UploaderBookMapper extends AbstractModelMapper<UploaderBookModel,UploaderBookDomain>
{
	@Override
	public Class<UploaderBookModel> entityType() {
		
		return UploaderBookModel.class;
	}

	@Override
	public Class<UploaderBookDomain> modelType() {
		
		return UploaderBookDomain.class;
	}
	}