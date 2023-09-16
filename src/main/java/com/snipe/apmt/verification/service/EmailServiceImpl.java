package com.snipe.apmt.verification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.snipe.apmt.config.EmailConfig;
import com.snipe.apmt.domain.UserDomain;

@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	EmailConfig emailConfig;
	
	@Override
	public void sendProjectRejectionMessage(String from, String to) {
		SimpleMailMessage message = emailConfig.emailProjectRejectionTemplate(from, to);
		mailSender.send(message);
		System.out.println("Project Rejection mail sent");
	}

	@Override
	public void sendBookRejectionMessage(String from, String to) {
		SimpleMailMessage message = emailConfig.emailBookRejectionTemplate(from, to);
		mailSender.send(message);
		System.out.println("Book Rejection mail sent");
	}
	
	@Override
	public void sendArticleRejectionMessage(String from, String to) {
		SimpleMailMessage message = emailConfig.emailArticleRejectionTemplate(from, to);
		mailSender.send(message);
		System.out.println("Article Rejection mail sent");
	}

	@Override
	public void sendUploadSuccessMessage(String From, String ToEmail, String[] Cc) {
		SimpleMailMessage message = emailConfig.sendUploadSuccessMessage(From, ToEmail,Cc);
		mailSender.send(message);
		System.out.println("Uploaded mail sent to sm , vm and uploader ");
		
	}
	
	@Override
	public void sendUploadBookSuccessMessage(String From, String ToEmail, String[] Cc) {
		SimpleMailMessage message = emailConfig.sendUploadBookSuccessMessage(From, ToEmail,Cc);
		mailSender.send(message);
		System.out.println("Uploaded mail sent to sm , vm and uploader ");
		
	}
	
	@Override
	public void sendUploadArticleSuccessMessage(String From, String ToEmail, String[] Cc) {
		SimpleMailMessage message = emailConfig.sendUploadArticleSuccessMessage(From,ToEmail,Cc);
		mailSender.send(message);
		System.out.println("Uploaded mail sent to sm , vm and uploader ");
		
	}

	@Override
	public void sendOtpMessage(String from, String EmailTo, int otp) {
		SimpleMailMessage messages = emailConfig.sendOtpMessage(from, EmailTo, otp);
		mailSender.send(messages);
		System.out.println("Otp sent successfully ");
		
	}
	
	public void sendWelcomeMessage(String from,String to) {
		SimpleMailMessage messages = emailConfig.sendWelcomeMessage(from,to);
		mailSender.send(messages);
		System.out.println("welcome message sent successfully ");
		
	}

	@Override
	public void sendProjectApprovedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendApprovedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Approved message sent successfully ");
		
	}

	@Override
	public void sendProjectVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendVerifiedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Verified message sent successfully ");
		
	}

	@Override
	public void sendBookApprovedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendBookApprovedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Approved  message sent successfully ");
		
	}

	@Override
	public void sendBookVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendBookVerifiedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Verified  message sent successfully ");
		
	}

	@Override
	public void sendArticleApprovedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendArticleApprovedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Approved  message sent successfully ");
		
	}

	@Override
	public void sendArticleVerifiedMessage(String fromEmail, String emailId) {
		SimpleMailMessage messages = emailConfig.sendArticleVerifiedMessage(fromEmail,emailId);
		mailSender.send(messages);
		System.out.println("Verified  message sent successfully ");
		
	}

	
}
