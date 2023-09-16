package com.snipe.apmt.verification.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.SendUploadedEmailTemplate;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.verification.constant.ProjectStatusCode;
import com.snipe.apmt.verification.dao.VerificationRepository;
import com.snipe.apmt.verification.domain.VerificationDomain;
import com.snipe.apmt.verification.mapper.VerificationMapper;
import com.snipe.apmt.verification.model.VerificationModel;

@Service
public class VerificationServiceImpl implements IVerificationService {

	@Autowired
	UploaderRepository uploaderRepository;
	
	@Autowired
    UserDAORepository userDaoRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	UploaderMapper uploaderMapper;

	//@Autowired
	//IEmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	UserDAORepository userDAORepository;

	@Autowired
	VerificationMapper verificationMapper;
	
		
	@Autowired
	IEmailService emailService;
		
	private static final Logger logger = LoggerFactory.getLogger(VerificationServiceImpl.class);

	@Override
	public List<UploaderModel> getVerifiedProjects() throws Exception {
		try {
			List<UploaderDomain> projectDomain = uploaderRepository
					.findProjectsByStatus(ProjectStatusCode.VERIFIED.getCode());
			return uploaderMapper.entityList(projectDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public List<UploaderModel> getNewProjects() throws Exception {
		try {
			List<UploaderDomain> projectDomain = uploaderRepository
					.findProjectsByStatus(ProjectStatusCode.NEW.getCode());
			return uploaderMapper.entityList(projectDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

	@Override
	public List<UploaderModel> getProjectsListByCategoryId(int categoryID) throws Exception {
		try {
			List<UploaderDomain> projectDomain = uploaderRepository.getProjectsListByCategoryId(categoryID);

			return uploaderMapper.entityList(projectDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	/*
	 * verifiedStatus - Value coming from frontend via checkbox. Value = 1 - true .
	 * Based on this value, the project table status is updated as VERIFIED using an
	 * ENUM Project Status Code
	 */

//	@Override
//	public String verifyProject(long projectId, boolean verifiedStatus) throws Exception {
//		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
//
//		if (uploaderDomain.getStatus() == ProjectStatusCode.VERIFIED.getCode()) {
//			throw new Exception(" Project already verified");
//		}
//
//		else if (verifiedStatus) {
//
//			uploaderDomain.setStatus(ProjectStatusCode.VERIFIED.getCode());
//
//			// updates the project table status to verified.
//			uploaderRepository.updateProjectStatus(uploaderDomain.getStatus(), uploaderDomain.getProjectId());
//			// adds an entry to the verification table.
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Verifying Project", uploaderDomain.getTitle());
//			String verifierName = userDomain.getFirstName() + userDomain.getLastName();
//			addVerifiedEntry(uploaderDomain, verifierName);
//
//			return "Project Verified";
//		} else {
//			throw new Exception("Project is not verified");
//		}
//
//	}
//
//	public void addVerifiedEntry(UploaderDomain uploaderDomain, String verifierName) {
//
//		VerificationDomain verificationDomain = new VerificationDomain();
//
//		if (uploaderDomain != null) {
//			verificationDomain.setProjectId(uploaderDomain.getProjectId());
//			verificationDomain.setVerifierName(verifierName);
//			verificationDomain.setStatusCode(uploaderDomain.getStatus());
//			verificationDomain.setStatusDesc(ProjectStatusCode.VERIFIED.getDesc());
//			verificationDomain.setProjectVerifiedDate(LocalDateTime.now());
//			// Inserting record into activity log details table
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Project Verified", uploaderDomain.getTitle());
//			verificationRepository.save(verificationDomain);
//
//		}
//	}
	
	
	@Override
	public String verifyProject(VerificationModel verificationModel) throws Exception {
		long projectId = verificationModel.getProjectId();
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		if(uploaderDomain==null) {
			throw new Exception("Project not found");
			
		}if (uploaderDomain.getStatus() == ProjectStatusCode.VERIFIED.getCode()) {
			throw new Exception("Project already verified");
		}
		else if (verificationModel.getStatusCode()==ProjectStatusCode.VERIFIED.getCode()) {
		VerificationDomain verificationDomain = verificationMapper.model(verificationModel);
		verificationDomain.setStatusDesc(ProjectStatusCode.VERIFIED.getDesc());			
		 verificationRepository.save(verificationDomain);		
		uploaderDomain.setStatus(ProjectStatusCode.VERIFIED.getCode());
		uploaderRepository.updateProjectStatus(uploaderDomain.getStatus(), uploaderDomain.getProjectId());
		
		//added for verfied email
		Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationModel.getVerifierId());
		       String VEmailId =  FromEmail.get().getEmailId();
		emailService.sendProjectVerifiedMessage(VEmailId, uploaderDomain.getEmailId());
		return "project verified";
		}else {
			throw new Exception("Project is not verified");
		}
		
	}

	@Override
	public String approveProject(long projectId, boolean approveStatus) throws Exception {

		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		boolean projectVerifiedStatus = isProjectVerified(uploaderDomain);

		if (projectVerifiedStatus && approveStatus) {
			uploaderDomain.setStatus(ProjectStatusCode.APPROVED.getCode());

			// updates the project table status to Approved.
			uploaderRepository.updateProjectStatus(uploaderDomain.getStatus(), uploaderDomain.getProjectId());
			// updates entry in the verification table.
			addApprovedEntry(uploaderDomain);
			// Inserting record into activity log details table
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Project Approved", uploaderDomain.getTitle());
			
			//added for approved email
							
			VerificationDomain verificationDomain = verificationRepository.findByProjectId(uploaderDomain.getProjectId());
			Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationDomain.getVerifierId());
		    String VEmailId =  FromEmail.get().getEmailId();
		    emailService.sendProjectApprovedMessage(VEmailId, uploaderDomain.getEmailId());			
			return "Project Approved";
		} else {
			throw new Exception("Error in approving the project");
		}
	}

	@Override
	public String rejectProject(long projectId, boolean rejectStatus) throws Exception {

		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		boolean projectVerifiedStatus = isProjectVerified(uploaderDomain);

		String uploaderEmail = getUploaderEmail(uploaderDomain);

		if (projectVerifiedStatus && rejectStatus == false) {
			uploaderDomain.setStatus(ProjectStatusCode.REJECTED.getCode());

			// updates the project table status to Rejected.
			uploaderRepository.updateProjectStatus(uploaderDomain.getStatus(), uploaderDomain.getProjectId());
			// updates the entry in the verification table.
			addRejectedEntry(uploaderDomain);

			// Inserting record into activity log details table
			UserDomain userDomain= (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Project Rejected", uploaderDomain.getTitle());

			String FromEmail= userDaoRepository.getEMailIdByRoleId();
			emailService.sendProjectRejectionMessage(FromEmail, uploaderDomain.getEmailId());
          
			return "Project Rejected";
		} else {
			throw new Exception("Error in rejecting the project");
		}

	}

		
	
	private boolean isProjectVerified(UploaderDomain uploaderDomain) throws Exception {

		if (uploaderDomain != null) {
			if (uploaderDomain.getStatus() == ProjectStatusCode.VERIFIED.getCode()) {
				return true;
			} else {
				throw new Exception(" Verify the project to approve or reject ");
			}
		}
		return false;
	}

	public void addApprovedEntry(UploaderDomain uploaderDomain) {
		try {
			VerificationDomain verificationDomain = verificationRepository
					.findByProjectId(uploaderDomain.getProjectId());
			if (verificationDomain != null) {

				verificationDomain.setStatusCode(uploaderDomain.getStatus());
				verificationDomain.setStatusDesc(ProjectStatusCode.APPROVED.getDesc());
				verificationDomain.setProjectApprovedDate(LocalDateTime.now());

				verificationRepository.updateStatus(verificationDomain.getStatusCode(),
						verificationDomain.getStatusDesc(), verificationDomain.getProjectApprovedDate(),
						verificationDomain.getProjectId());

			}

		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	public void addRejectedEntry(UploaderDomain uploaderDomain) {
		try {
			VerificationDomain verificationDomain = verificationRepository
					.findByProjectId(uploaderDomain.getProjectId());
			if (verificationDomain != null) {

				verificationDomain.setStatusCode(uploaderDomain.getStatus());
				verificationDomain.setStatusDesc(ProjectStatusCode.REJECTED.getDesc());
				verificationDomain.setProjectRejectedDate(LocalDateTime.now());

				verificationRepository.updateRejectedStatus(verificationDomain.getStatusCode(),
						verificationDomain.getStatusDesc(), verificationDomain.getProjectRejectedDate(),
						verificationDomain.getProjectId());

			}
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

	public String getUploaderEmail(UploaderDomain uploaderDomain) throws Exception {
		if (uploaderDomain != null) {

			Optional<UserDomain> findByUploaderId = userDAORepository.findById(uploaderDomain.getUploaderId());

			return findByUploaderId.get().getEmailId();
		} else {
			throw new Exception("  Unable to fetch Uploader Id email details");
		}
	}

	@Override
	public List<VerificationModel> getVerificationDetails() throws Exception {
		try {
			List<VerificationDomain> verificationDomain = verificationRepository.findAll();

			return verificationMapper.entityList(verificationDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public VerificationModel getVerificationDetailsByID(long projectId) throws Exception {
		try {

			VerificationModel verificationModel = new VerificationModel();
			VerificationDomain verificationDomain = verificationRepository.findByProjectId(projectId);
			BeanUtils.copyProperties(verificationDomain, verificationModel);
			return verificationModel;
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

}
