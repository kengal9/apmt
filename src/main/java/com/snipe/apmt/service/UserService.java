package com.snipe.apmt.service;

import java.util.List;

import javax.mail.MessagingException;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;

public interface UserService {

	public UserModel saveUserInfo(UserModel userModel) throws Exception;

	public List<UserModel> getUserDetailByRoleId(int roleId, boolean status, boolean employeeStatus) throws Exception;

	public UserModel getUserById(String id, boolean status) throws Exception;

	public String updateStatus(UserDomain userDomain) throws Exception;

	public String deleteUser(String id) throws Exception;

	public UserDomain login(UserModel userModel) throws Exception;

	public long getUserCount(int roleId, boolean status) throws Exception;
	
	public  void saveUserLogDetails(UserDomain userDomain,String operation,String projectTitle);

	public UserDomain oauthLogin(UserModel userModel) throws Exception;

	public int generateOTP(String id) throws MessagingException;

	//public String validateOTP(int otpnum)throws MessagingException;

	public String validateOTP(int otpnum, String id) throws MessagingException;


}
