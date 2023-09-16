package com.snipe.apmt.verification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderArticleMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.verification.constant.ArticleStatusCode;
import com.snipe.apmt.verification.constant.ArticleStatusCode;
import com.snipe.apmt.verification.dao.VerificationArticleRepository;
import com.snipe.apmt.verification.domain.VerificationArticleDomain;
import com.snipe.apmt.verification.domain.VerificationBookDomain;
import com.snipe.apmt.verification.domain.VerificationDomain;
import com.snipe.apmt.verification.mapper.VerificationArticleMapper;
import com.snipe.apmt.verification.model.VerificationArticleModel;
import com.snipe.apmt.verification.model.VerificationModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class VerificationArticleServiceImpl implements VerificationArticleService {

	@Autowired
	UploaderArticleRepository uploaderArticleRepository;
	
	@Autowired
    UserDAORepository userDaoRepository;

	@Autowired
	VerificationArticleRepository verificationArticleRepository;

	@Autowired
	UploaderArticleMapper uploaderArticleMapper;

	@Autowired
	IEmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	UserDAORepository userDAORepository;

	@Autowired
	VerificationArticleMapper verificationArticleMapper;

	private static final Logger logger = LoggerFactory.getLogger(VerificationArticleServiceImpl.class);

	@Override
	public List<UploaderArticleModel> getVerifiedArticles() throws Exception {
		try {
			List<UploaderArticleDomain> articleDomain = uploaderArticleRepository
					.findArticlesByStatus(ArticleStatusCode.VERIFIED.getCode());
			return uploaderArticleMapper.entityList(articleDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public List<UploaderArticleModel> getNewArticles() throws Exception {
		try {
			List<UploaderArticleDomain> articleDomain = uploaderArticleRepository
					.findArticlesByStatus(ArticleStatusCode.NEW.getCode());
			return uploaderArticleMapper.entityList(articleDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

	@Override
	public List<UploaderArticleModel> getArticlesListByCategoryId(int categoryID) throws Exception {
		try {
			List<UploaderArticleDomain> uploaderArticleDomain = uploaderArticleRepository
					.getArticlesListByCategoryId(categoryID);
			return uploaderArticleMapper.entityList(uploaderArticleDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

//	@Override
//	public String verifyArticle(long articleId, boolean verifiedStatus) throws Exception {
//
//		UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository.getByArticleId(articleId);
//		if (uploaderArticleDomain.getStatus() == ArticleStatusCode.VERIFIED.getCode()) {
//			throw new Exception(" Article already verified");
//		}
//
//		else if (verifiedStatus) {
//			uploaderArticleDomain.setStatus(ArticleStatusCode.VERIFIED.getCode());
//			uploaderArticleRepository.updateArticleStatus(uploaderArticleDomain.getStatus(),
//					uploaderArticleDomain.getArticleId());
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Verifying Article", uploaderArticleDomain.getTitle());
//			String verifierName = userDomain.getFirstName() + userDomain.getLastName();
//			addVerifiedEntry(uploaderArticleDomain, verifierName);
//			return "Article Verified";
//		} else {
//			throw new Exception("Article is not verified");
//		}
//	}
//
//	private void addVerifiedEntry(UploaderArticleDomain uploaderArticleDomain, String verifierName) {
//		VerificationArticleDomain verificationArticleDomain = new VerificationArticleDomain();
//
//		if (uploaderArticleDomain != null) {
//			verificationArticleDomain.setArticleId(uploaderArticleDomain.getArticleId());
//			verificationArticleDomain.setVerifierName(verifierName);
//			verificationArticleDomain.setStatusCode(uploaderArticleDomain.getStatus());
//			verificationArticleDomain.setStatusDesc(ArticleStatusCode.VERIFIED.getDesc());
//			verificationArticleDomain.setArticleVerifiedDate(LocalDateTime.now());
//			// Inserting record into activity log details table
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Article Verified", uploaderArticleDomain.getTitle());
//			verificationArticleRepository.save(verificationArticleDomain);
//
//		}
//	}
	
	
	
	@Override
	public String verifyArticle(VerificationArticleModel verificationArticleModel) throws Exception {
		long ArticleId = verificationArticleModel.getArticleId();
		UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository.getByArticleId(ArticleId);
		if(uploaderArticleDomain==null) {
			throw new Exception("Article not found");
		}if (uploaderArticleDomain.getStatus() == ArticleStatusCode.VERIFIED.getCode()) {
			throw new Exception("Article already verified");
		}
		else if (verificationArticleModel.getStatusCode()==ArticleStatusCode.VERIFIED.getCode()) {
		VerificationArticleDomain verificationArticleDomain = verificationArticleMapper.model(verificationArticleModel);
		verificationArticleDomain.setStatusDesc(ArticleStatusCode.VERIFIED.getDesc());
		verificationArticleRepository.save(verificationArticleDomain);
		uploaderArticleDomain.setStatus(ArticleStatusCode.VERIFIED.getCode());
		uploaderArticleRepository.updateArticleStatus(uploaderArticleDomain.getStatus(), uploaderArticleDomain.getArticleId());
		
		
		Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationArticleModel.getVerifierId());
	    String VEmailId =  FromEmail.get().getEmailId();
	    emailService.sendArticleVerifiedMessage(VEmailId, uploaderArticleDomain.getEmailId());	
		
		return "Article verified";
		}else {
			throw new Exception("Article is not verified");
		}
	}
		
	
	

	@Override
	public String approveArticle(long articleId, boolean approveStatus) throws Exception {

		UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository.getByArticleId(articleId);
		boolean articleVerifiedStatus = isArticleVerified(uploaderArticleDomain);

		if (articleVerifiedStatus && approveStatus) {
			uploaderArticleDomain.setStatus(ArticleStatusCode.APPROVED.getCode());
			uploaderArticleRepository.updateArticleStatus(uploaderArticleDomain.getStatus(),
					uploaderArticleDomain.getArticleId());
			addApprovedEntry(uploaderArticleDomain);
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Article Approved", uploaderArticleDomain.getTitle());
			
			//added for Approved email
			VerificationArticleDomain verificationArticleDomain = verificationArticleRepository.findByArticleId(uploaderArticleDomain.getArticleId());
			Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationArticleDomain.getVerifierId());
		    String VEmailId =  FromEmail.get().getEmailId();
		    emailService.sendArticleApprovedMessage(VEmailId, uploaderArticleDomain.getEmailId());
			return "Article Approved";
		} else {
			throw new Exception("Error in approving the Article");
		}
	}

	private void addApprovedEntry(UploaderArticleDomain uploaderArticleDomain) {
		try {
			VerificationArticleDomain verificationArticleDomain = verificationArticleRepository
					.findByArticleId(uploaderArticleDomain.getArticleId());
			if (verificationArticleDomain != null) {

				verificationArticleDomain.setStatusCode(uploaderArticleDomain.getStatus());
				verificationArticleDomain.setStatusDesc(ArticleStatusCode.APPROVED.getDesc());
				verificationArticleDomain.setArticleApprovedDate(LocalDateTime.now());

				verificationArticleRepository.updateStatus(verificationArticleDomain.getStatusCode(),
						verificationArticleDomain.getStatusDesc(), verificationArticleDomain.getArticleApprovedDate(),
						verificationArticleDomain.getArticleId());

			}

		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public String rejectArticle(long articleId, boolean rejectStatus) throws Exception {
		UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository.getByArticleId(articleId);
		boolean articleVerifiedStatus = isArticleVerified(uploaderArticleDomain);

		//String uploaderEmail = getUploaderEmail(uploaderArticleDomain);

		if (articleVerifiedStatus && rejectStatus == false) {
			uploaderArticleDomain.setStatus(ArticleStatusCode.REJECTED.getCode());
			uploaderArticleRepository.updateArticleStatus(uploaderArticleDomain.getStatus(),
					uploaderArticleDomain.getArticleId());
			addRejectedEntry(uploaderArticleDomain);
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Article Rejected", uploaderArticleDomain.getTitle());
						
			VerificationArticleDomain verificationArticleDomain = verificationArticleRepository.findByArticleId(uploaderArticleDomain.getArticleId());
			Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationArticleDomain.getVerifierId());
		    String VEmailId =  FromEmail.get().getEmailId();
		    emailService.sendArticleRejectionMessage(VEmailId, uploaderArticleDomain.getEmailId());
			return "Article Rejected";
		} else {
			throw new Exception("Error in rejecting the Article");
		}
	}

	private void addRejectedEntry(UploaderArticleDomain uploaderArticleDomain) {
		try {
			VerificationArticleDomain verificationArticleDomain = verificationArticleRepository
					.findByArticleId(uploaderArticleDomain.getArticleId());
			if (verificationArticleDomain != null) {

				verificationArticleDomain.setStatusCode(uploaderArticleDomain.getStatus());
				verificationArticleDomain.setStatusDesc(ArticleStatusCode.REJECTED.getDesc());
				verificationArticleDomain.setArticleRejectedDate(LocalDateTime.now());

				verificationArticleRepository.updateRejectedStatus(verificationArticleDomain.getStatusCode(),
						verificationArticleDomain.getStatusDesc(), verificationArticleDomain.getArticleRejectedDate(),
						verificationArticleDomain.getArticleId());

			}
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	private String getUploaderEmail(UploaderArticleDomain uploaderArticleDomain) throws Exception {
		if (uploaderArticleDomain != null) {

			Optional<UserDomain> findByUploaderId = userDAORepository.findById(uploaderArticleDomain.getUploaderId());

			return findByUploaderId.get().getEmailId();
		} else {
			throw new Exception("  Unable to fetch Uploader Id email details");
		}
	}

	private boolean isArticleVerified(UploaderArticleDomain uploaderArticleDomain) throws Exception {
		if (uploaderArticleDomain != null) {
			if (uploaderArticleDomain.getStatus() == ArticleStatusCode.VERIFIED.getCode()) {
				return true;
			} else {
				throw new Exception(" Verify the article to approve or reject ");
			}
		}
		return false;
	}
	
	@Override
	public List<VerificationArticleModel> getVerificationArticleDetails() throws Exception {
		try {
			List<VerificationArticleDomain> verificationArticleDomain = verificationArticleRepository.findAll();

			return verificationArticleMapper.entityList(verificationArticleDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}
	
	@Override
	public VerificationArticleModel getVerificationArticleDetailsByID(long articleId) throws Exception {
		try {

			VerificationArticleModel verificationArticleModel = new VerificationArticleModel();
			VerificationArticleDomain verificationArticleDomain = verificationArticleRepository.findByArticleId(articleId);
			BeanUtils.copyProperties(verificationArticleDomain, verificationArticleModel);
			return verificationArticleModel;
		} catch (Exception e) {
			logger.error("Exception created in VerificationArticleServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

}
