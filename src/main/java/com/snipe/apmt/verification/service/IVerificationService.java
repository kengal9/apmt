
package com.snipe.apmt.verification.service;

import java.util.List;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.verification.model.VerificationModel;

public interface IVerificationService {

	public List<UploaderModel> getVerifiedProjects() throws Exception;

	public List<UploaderModel> getNewProjects() throws Exception;

	public List<UploaderModel> getProjectsListByCategoryId(int categoryID) throws Exception;

//	public String verifyProject(long projectId, boolean verifiedStatus) throws Exception;
	public String verifyProject(VerificationModel verificationModel) throws Exception;

	public String approveProject(long projectId, boolean approveStatus) throws Exception;

	public String rejectProject(long projectId, boolean rejectStatus) throws Exception;

	public List<VerificationModel> getVerificationDetails() throws Exception;
	
	public VerificationModel getVerificationDetailsByID(long projectId)throws Exception;

}
