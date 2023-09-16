package com.snipe.apmt.verification.service;

import java.util.List;
import java.util.Optional;

import com.snipe.apmt.domain.UserDomain;

public interface IEmailService {

	public void sendProjectRejectionMessage(String from, String to);

	public void sendBookRejectionMessage(String from, String to);

	public void sendArticleRejectionMessage(String from, String to);
	
	public void sendUploadSuccessMessage(String From, String toEmail, String[]Cc);

	public void sendUploadBookSuccessMessage(String From, String toEmail, String[] Cc);

	public void sendUploadArticleSuccessMessage(String From, String toEmail, String[] Cc);

	public void sendOtpMessage(String from, String EmailTo, int otp);
	
	public void sendWelcomeMessage(String string, String EmailTo);

	public void sendProjectApprovedMessage(String fromEmail, String emailId);

	public void sendProjectVerifiedMessage(String fromEmail, String emailId);

	public void sendBookApprovedMessage(String fromEmail, String emailId);

	public void sendBookVerifiedMessage(String fromEmail, String emailId);

	public void sendArticleApprovedMessage(String fromEmail, String emailId);

	public void sendArticleVerifiedMessage(String fromEmail, String emailId);

	

	
	
	
	

	

}
