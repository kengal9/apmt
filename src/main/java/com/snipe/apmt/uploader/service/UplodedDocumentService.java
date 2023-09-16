package com.snipe.apmt.uploader.service;

import java.io.IOException;

import com.snipe.apmt.uploader.model.UploaderModel;

public interface UplodedDocumentService {

	public UploaderModel getProjectImageById(long projectId, String mediaType) throws IOException, Exception;
	
	

}
