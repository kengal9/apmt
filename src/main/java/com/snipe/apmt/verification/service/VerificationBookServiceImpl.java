package com.snipe.apmt.verification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.mapper.UploaderBookMapper;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.verification.constant.BookStatusCode;
import com.snipe.apmt.verification.dao.VerificationBookRepository;
import com.snipe.apmt.verification.domain.VerificationBookDomain;
import com.snipe.apmt.verification.domain.VerificationDomain;
import com.snipe.apmt.verification.mapper.VerificationBookMapper;
import com.snipe.apmt.verification.model.VerificationBookModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class VerificationBookServiceImpl implements VerificationBookService{

	@Autowired
	UploaderBookRepository uploaderBookRepository;
	
	@Autowired
    UserDAORepository userDaoRepository;
	@Autowired
	VerificationBookRepository verificationBookRepository;

	@Autowired
	UploaderBookMapper uploaderBookMapper;

	@Autowired
	IEmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	UserDAORepository userDAORepository;

	@Autowired
	VerificationBookMapper verificationBookMapper;

	private static final Logger logger = LoggerFactory.getLogger(VerificationBookServiceImpl.class);


	
	@Override
	public List<UploaderBookModel> getVerifiedBooks() throws Exception {		
		try {
			List<UploaderBookDomain> bookDomain = uploaderBookRepository.
					findBookByStatus(BookStatusCode.VERIFIED.getCode());
			return uploaderBookMapper.entityList(bookDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationBookServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public List<UploaderBookModel> getNewBooks() throws Exception {
		try {
			List<UploaderBookDomain> bookDomain = uploaderBookRepository.
					findBookByStatus(BookStatusCode.NEW.getCode());
			return uploaderBookMapper.entityList(bookDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationBookServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

	@Override
	public List<UploaderBookModel> getBooksListByCategoryId(int categoryID) throws Exception {
		try {
			List<UploaderBookDomain>uploaderBookDomain = uploaderBookRepository.getBooksListByCategoryId(categoryID);
			return uploaderBookMapper.entityList(uploaderBookDomain);
		    }
	catch(Exception e)
		{
		logger.error("Exception created in VerificationBookServiceImplementation: ", e.getMessage());
		throw new BACKEND_SERVER_ERROR();
		}
		
	}

//	@Override
//	public String verifyBook(long bookId, boolean verifiedStatus) throws Exception {
//		
//		UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(bookId);
//		if (uploaderBookDomain.getStatus() == BookStatusCode.VERIFIED.getCode()) {
//			throw new Exception(" Book already verified");
//		}
//
//		else if (verifiedStatus) {
//			uploaderBookDomain.setStatus(BookStatusCode.VERIFIED.getCode());
//			uploaderBookRepository.updateBookStatus(uploaderBookDomain.getStatus(), uploaderBookDomain.getBookId());
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Verifying Book", uploaderBookDomain.getTitle());
//			String verifierName = userDomain.getFirstName() + userDomain.getLastName();
//			addVerifiedEntry(uploaderBookDomain, verifierName);
//			return "Book Verified";
//		} else {
//			throw new Exception("Book is not verified");
//		}
//	}
//
//	private void addVerifiedEntry(UploaderBookDomain uploaderBookDomain, String verifierName) {
//		VerificationBookDomain verificationBookDomain = new VerificationBookDomain();
//
//		if (uploaderBookDomain != null) {
//			verificationBookDomain.setBookId(uploaderBookDomain.getBookId());
//			verificationBookDomain.setVerifierName(verifierName);
//			verificationBookDomain.setStatusCode(uploaderBookDomain.getStatus());
//			verificationBookDomain.setStatusDesc(BookStatusCode.VERIFIED.getDesc());
//			verificationBookDomain.setBookVerifiedDate(LocalDateTime.now());
//			// Inserting record into activity log details table
//			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//			userService.saveUserLogDetails(userDomain, "Book Verified", uploaderBookDomain.getTitle());
//			verificationBookRepository.save(verificationBookDomain);
//
//		}
//		
//	}
	
	@Override
	public String verifyBook(VerificationBookModel verificationBookModel) throws Exception {
		long BookId = verificationBookModel.getBookId();
		UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(BookId);
		if(uploaderBookDomain==null) {
			throw new Exception("Book not found");
		}if (uploaderBookDomain.getStatus() == BookStatusCode.VERIFIED.getCode()) {
			throw new Exception("Book already verified");
		}
		else if (verificationBookModel.getStatusCode()==BookStatusCode.VERIFIED.getCode()) {
		VerificationBookDomain verificationBookDomain = verificationBookMapper.model(verificationBookModel);
		verificationBookDomain.setStatusDesc(BookStatusCode.VERIFIED.getDesc());
		verificationBookRepository.save(verificationBookDomain);
		uploaderBookDomain.setStatus(BookStatusCode.VERIFIED.getCode());
		uploaderBookRepository.updateBookStatus(uploaderBookDomain.getStatus(), uploaderBookDomain.getBookId());
		
		//added for verfied email
		
		Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationBookModel.getVerifierId());
	    String VEmailId =  FromEmail.get().getEmailId();
	    emailService.sendBookVerifiedMessage(VEmailId, uploaderBookDomain.getEmailId());
		return "Book verified";
		}else {
			throw new Exception("Book is not verified");
		}
	}

	@Override
	public String approveBook(long bookId, boolean approveStatus) throws Exception {
		
		UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(bookId);
		boolean bookVerifiedStatus = isBookVerified(uploaderBookDomain);

		if (bookVerifiedStatus && approveStatus) {
			uploaderBookDomain.setStatus(BookStatusCode.APPROVED.getCode());
			uploaderBookRepository.updateBookStatus(uploaderBookDomain.getStatus(), uploaderBookDomain.getBookId());
			addApprovedEntry(uploaderBookDomain);
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Book Approved", uploaderBookDomain.getTitle());
			
			//added for Approved email
			
			VerificationBookDomain verificationBookDomain = verificationBookRepository.findByBookId(uploaderBookDomain.getBookId());
			Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationBookDomain.getVerifierId());
		    String VEmailId =  FromEmail.get().getEmailId();
		    emailService.sendBookApprovedMessage(VEmailId, uploaderBookDomain.getEmailId());			
			return "Book Approved";
		} else {
			throw new Exception("Error in approving the Book");
		}
	}

	private void addApprovedEntry(UploaderBookDomain uploaderBookDomain) {
		try {
			VerificationBookDomain verificationBookDomain = verificationBookRepository.
					findByBookId(uploaderBookDomain.getBookId());
			if (verificationBookDomain != null) {

				verificationBookDomain.setStatusCode(uploaderBookDomain.getStatus());
				verificationBookDomain.setStatusDesc(BookStatusCode.APPROVED.getDesc());
				verificationBookDomain.setBookApprovedDate(LocalDateTime.now());

				verificationBookRepository.updateStatus(verificationBookDomain.getStatusCode(),
				verificationBookDomain.getStatusDesc(), verificationBookDomain.getBookApprovedDate(),
				verificationBookDomain.getBookId());

			}

		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

		
	}

	@Override
	public String rejectBook(long bookId, boolean rejectStatus) throws Exception {
		UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(bookId);
		boolean bookVerifiedStatus = isBookVerified(uploaderBookDomain);

		//String uploaderEmail = getUploaderEmail(uploaderBookDomain);

		if (bookVerifiedStatus && rejectStatus == false) {
			uploaderBookDomain.setStatus(BookStatusCode.REJECTED.getCode()); 
			uploaderBookRepository.updateBookStatus(uploaderBookDomain.getStatus(), uploaderBookDomain.getBookId());
			addRejectedEntry(uploaderBookDomain);
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "Book Rejected", uploaderBookDomain.getTitle());
			
			
			VerificationBookDomain verificationBookDomain = verificationBookRepository.findByBookId(uploaderBookDomain.getBookId());
			Optional<UserDomain> FromEmail= userDaoRepository.findById(verificationBookDomain.getVerifierId());
		    String VEmailId =  FromEmail.get().getEmailId();
		    emailService.sendBookRejectionMessage(VEmailId, uploaderBookDomain.getEmailId());
			return "Book Rejected";
		} else {
			throw new Exception("Error in rejecting the Book");
		}
	}

	private void addRejectedEntry(UploaderBookDomain uploaderBookDomain) {
		try {
			VerificationBookDomain verificationBookDomain = verificationBookRepository
					.findByBookId(uploaderBookDomain.getBookId());
			if (verificationBookDomain != null) {

				verificationBookDomain.setStatusCode(uploaderBookDomain.getStatus());
				verificationBookDomain.setStatusDesc(BookStatusCode.REJECTED.getDesc());
				verificationBookDomain.setBookRejectedDate(LocalDateTime.now());

				verificationBookRepository.updateRejectedStatus(verificationBookDomain.getStatusCode(),
						verificationBookDomain.getStatusDesc(), verificationBookDomain.getBookRejectedDate(),
						verificationBookDomain.getBookId());

			}
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		
	}

	private String getUploaderEmail(UploaderBookDomain uploaderBookDomain) throws Exception {
		
	
		if (uploaderBookDomain != null) {

			Optional<UserDomain> findByUploaderId = userDAORepository.findById(uploaderBookDomain.getUploaderId());

			return findByUploaderId.get().getEmailId();
		} else {
			throw new Exception("  Unable to fetch Uploader Id email details");
		}
		
	}

	private boolean isBookVerified(UploaderBookDomain uploaderBookDomain) throws Exception {
		if (uploaderBookDomain != null) {
			if (uploaderBookDomain.getStatus() == BookStatusCode.VERIFIED.getCode()) {
				return true;
			} else {
				throw new Exception(" Verify the project to approve or reject ");
			}
		}
		return false;
	}

	@Override
	public List<VerificationBookModel> getVerificationBookDetails() throws Exception {
		try {
			List<VerificationBookDomain> verificationBookDomain = verificationBookRepository.findAll();

			return verificationBookMapper.entityList(verificationBookDomain);
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public VerificationBookModel getVerificationBookDetailsByID(long bookId) throws Exception {
		try {

			VerificationBookModel verificationBookModel = new VerificationBookModel();
			VerificationBookDomain verificationBookDomain = verificationBookRepository.findByBookId(bookId);
			BeanUtils.copyProperties(verificationBookDomain, verificationBookModel);
			return verificationBookModel;
		} catch (Exception e) {
			logger.error("Exception created in VerificationServiceImplementation: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}
}


