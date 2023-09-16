package com.snipe.apmt.config;


import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.snipe.apmt.domain.UserDomain;

@Configuration
public class EmailConfig {
	
	public SimpleMailMessage emailProjectRejectionTemplate(String from, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject("Project Rejected email notification");
		message.setText("Dear User "  + "," + " Your project is rejected due to incorrect usage");
		return message;
	}
	
	public SimpleMailMessage emailBookRejectionTemplate(String from, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject("Book Rejected email notification");
		message.setText("Dear User "  + "," + "Your Book is rejected due to incorrect usage");
		return message;
	}
	
	public SimpleMailMessage emailArticleRejectionTemplate(String from, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setSubject("Article Rejected email notification");
		message.setText("Dear User "  + "," + " Your Article is rejected due to incorrect usage");
		return message;
	}
	
	
	public SimpleMailMessage sendUploadSuccessMessage(String from, String to,String[] Cc) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setCc(Cc);		
		message.setSubject("Project upload email notification");
		message.setText("Dear User "  + "," + " Your Project is uploaded Successfully.");
		return message;
	}
	
	public SimpleMailMessage sendUploadBookSuccessMessage(String from, String to,String[] Cc) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setCc(Cc);
		message.setSubject("Book upload email notification");
		message.setText("Dear User "  + "," + " Your Book is uploaded Successfully.");
		return message;
	}
	
	public SimpleMailMessage sendUploadArticleSuccessMessage(String from, String to,String[]Cc) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setFrom(from);
		message.setCc(Cc);
		message.setSubject("Article upload email notification");
		message.setText("Dear User "  + "," + " Your Article is uploaded Successfully.");
		return message;
	}
	
	public SimpleMailMessage sendOtpMessage(String from, String EmailTo, int otp) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(EmailTo);
		messages.setFrom(from);		
		messages.setSubject("APMT-OTP");
		messages.setText("Dear User "  + "," + " Your OTP number is "  +  otp + "");
		return messages;
	}
	
	public SimpleMailMessage sendWelcomeMessage(String from, String EmailTo) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(EmailTo);
		messages.setFrom(from);
		messages.setSubject("Welcome to APMT");
		messages.setText("Dear User "  + "," + " Your Registration is successfull. Welcome to APMT");
		return messages;
	}
	
	public SimpleMailMessage sendApprovedMessage(String from, String EmailTo) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(EmailTo);
		messages.setFrom(from);
		messages.setSubject("Project Approved Notification");
		messages.setText("Dear User "  + "," + "Your Project is Approved");
		return messages;
	}

	public SimpleMailMessage sendVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(emailId);
		messages.setFrom(fromEmail);
		messages.setSubject("Project Verified Notification");
		messages.setText("Dear User "  + "," + "Your Project is Verified");
		return messages;
	}

	public SimpleMailMessage sendBookVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(emailId);
		messages.setFrom(fromEmail);
		messages.setSubject("Book Verified Notification");
		messages.setText("Dear User "  + "," + "Your Book is Verified");
		return messages;
	}

	public SimpleMailMessage sendBookApprovedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(emailId);
		messages.setFrom(fromEmail);
		messages.setSubject("Book Approved Notification");
		messages.setText("Dear User "  + "," + "Your Book is Approved");
		return messages;
	}

	public SimpleMailMessage sendArticleVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(emailId);
		messages.setFrom(fromEmail);
		messages.setSubject("Article Verified Notification");
		messages.setText("Dear User "  + "," + "Your Article is Verified");
		return messages;
	}

	public SimpleMailMessage sendArticleApprovedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = new SimpleMailMessage();
		messages.setTo(emailId);
		messages.setFrom(fromEmail);
		messages.setSubject("Article Approved Notification");
		messages.setText("Dear User "  + "," + "Your Article is Approved");
		return messages;
	}

	
}
