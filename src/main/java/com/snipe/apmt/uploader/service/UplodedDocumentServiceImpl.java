package com.snipe.apmt.uploader.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.snipe.apmt.admin.service.StateServiceImpl;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.model.UploaderModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UplodedDocumentServiceImpl implements UplodedDocumentService {

	@Autowired
	UploaderRepository uploaderRepository;

	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	UploaderService uploaderService;

	@Value("${application.bucket.name}")
	private String bucketName;
	
	private static final Logger logger = LoggerFactory.getLogger(UplodedDocumentServiceImpl.class);

	@Override
	public UploaderModel getProjectImageById(long projectId, String mediaType) throws Exception {
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		UploaderModel uploaderModel = new UploaderModel();
		if(mediaType.equals("image")) {
			byte[] image = uploaderService.downloadFile(uploaderDomain.getImage());
			uploaderModel.setImage1(image);
		}else if(mediaType.equals("video")){
			byte[] video = uploaderService.downloadFile(uploaderDomain.getVideo());
			uploaderModel.setImage1(video);
		}else {
			logger.error("Exception create in UplodedDocumentServiceImpl media type didnot match");
			throw new Exception("media type didnot match");
		}
		return uploaderModel;
	}
	

}
