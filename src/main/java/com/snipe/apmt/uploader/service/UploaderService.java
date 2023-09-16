package com.snipe.apmt.uploader.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.model.UploaderModel;

public interface UploaderService {

	// Object getProjectListByUploaderId = null;

	public String saveProject(UploaderModel UploaderModel, MultipartFile image, MultipartFile video) throws Exception;

	public List<UploaderModel> getProjectList() throws Exception;

	public List<UploaderModel> getProjectListById(long projectId) throws Exception;

	// public List<UploaderModel> getProjectListByProjectId(long projectId) throws
	// Exception;

	public UploaderDomain updateProject(UploaderModel UploaderModel) throws Exception;

	public String deleteByProjectId(long projectId) throws Exception;

	// public String uploadFile(MultipartFile file) throws Exception;

	public String UploadFileInStorageService(MultipartFile file, String fileNameAndPath) throws Exception;

	public String deleteFile(String fileName) throws Exception;

	// Created for purpose of uploading/downloading file
	public UploaderModel getProjectByProjectId(long projectId) throws Exception;

	public byte[] downloadFile(String fileName) throws Exception;

	public List<UploaderModel> getProjectListByUploaderId(String uploaderId);

	public List<UploaderModel> getApprovedProjects();

	
	

	

}
