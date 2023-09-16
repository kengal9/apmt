package com.snipe.apmt.salesmanager.service;

import java.util.List;
import com.snipe.apmt.model.ProjectModel;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.verification.model.VerificationModel;

public interface SalesManagerService {

	public List<UploaderModel> getallSalesProjects( int categoryID) throws Exception;
	
	public List<UploaderModel> salesNewProjects() throws Exception;
	
	public String editPrice (long project_id,long  editPrice) throws Exception;
	
	/*public List<VerificationModel> getSalesNewProject()throws Exception;
	
	public List<UploaderModel> getProjectPrice(int categoryID) throws Exception ;*/
	
	//public List<UploaderModel> getallProjectsListByCategoryId() throws Exception ;
	//public List<UploaderModel> getPrice() throws Exception;

	/*
	 * public List<UploaderModel> getVerifiedProjects() throws Exception;
	 * 
	 * public List<UploaderModel> getNewProjects() throws Exception;
	 */
	/*public List<UploaderModel> getprojectsByCategory() throws Exception;
	
	public List<UploaderModel> getprojectsByCategoryID(int category) throws Exception;*/
	
	
	
	
	
}
