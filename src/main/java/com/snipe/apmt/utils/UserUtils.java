 package com.snipe.apmt.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Constants;
import com.snipe.apmt.constants.Role;
import com.snipe.apmt.dao.BankDAORepository;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.dao.UserRoleDaoRepository;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.UserRoleDomain;
import com.snipe.apmt.exception.APMTRestException.AADHAR_NUMBER_ALREADY_EXIST;
import com.snipe.apmt.exception.APMTRestException.ACCOUNT_NUMBER_ALREADY_EXIST;
import com.snipe.apmt.exception.APMTRestException.EMAILID_PATTERN_NOT_MATCH;
import com.snipe.apmt.exception.APMTRestException.FIRSTNAME_PATTERN_NOT_MATCH;
import com.snipe.apmt.exception.APMTRestException.NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.PAN_NUMBER_ALREADY_EXIST;
import com.snipe.apmt.exception.APMTRestException.PASSWORD_MISMATCH;
import com.snipe.apmt.exception.APMTRestException.PASSWORD_PATTERN_NOT_MATCHED;
import com.snipe.apmt.exception.APMTRestException.ROLE_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.USER_ALREADY_EXIST;
import com.snipe.apmt.exception.APMTRestException.VALIDATE;
import com.snipe.apmt.model.AddressModel;
import com.snipe.apmt.model.BankModel;
import com.snipe.apmt.model.UserModel;

@Component
public class UserUtils {

	@Autowired
	DataValidation dataValidation;

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	BankDAORepository bankDAORepository;

	@Autowired
	UserDAORepository userDAORepository;
	
	@Autowired
	UserRoleDaoRepository userRoleDaoRepository;

	@Autowired
	IDGeneration idGeneration;

	public boolean validateUserInfo(UserModel userModel) throws Exception {
		Role role = Role.getRole(userModel.getRoleId() + "");
		if (role == null)
			throw new ROLE_NOT_FOUND(userModel.getRoleId() + "");
		switch (role) {
		case ADMIN:
		case VERIFICATION_MANAGER:
		case SALES_MANAGER:
		case EMPLOYEE:
			validateCommonRoleProfile(userModel);
			validate(userModel, null, false);
		case STUDENT:
		case PURCHASER:
			validateCommonRoleProfile(userModel);
			break;
		default:
			break;
		}
		validatePassword(userModel);
		return true;
	}
	private void validateCommonRoleProfile(UserModel userModel) throws Exception {
	    if (userModel.getProfileSource().equalsIgnoreCase(Constants.PROFILE_SOURCE)) {
			validateMobileNumber(userModel.getMobileNumber());
			validateEmailFirstName(userModel);
			validateDobAndGender(userModel.getDob(), userModel.getGenderId());
			if (null == userModel.getAadharNumber()) {
				throw new NOT_FOUND("Please enter the aadharNumber");
			} else
				validateAadharNumber(userModel.getAadharNumber(), null, false);
	    }
	    else {
	    	throw new NOT_FOUND("Profile Source NOT FOUND");
	    }
	}

	public boolean validateAadharNumber(BigInteger aadharNumber, String id, boolean updateStatus) throws Exception {
		UserDomain profile = userDAORepository.validateAadhar(aadharNumber);
		if (null != profile) {
			if (updateStatus) {
				if (!id.equalsIgnoreCase(profile.getId()))
					throw new AADHAR_NUMBER_ALREADY_EXIST();
			} else
				throw new AADHAR_NUMBER_ALREADY_EXIST();
		}
		return true;
	}

	public boolean validateMobileNumber(long mobileNumber) {
		if (0 == mobileNumber)
			throw new NOT_FOUND("Please enter mobileNumber");
		return true;
	}

	public boolean validateEmailFirstName(UserModel userModel) throws Exception {
		validateMobileNumber(userModel.getMobileNumber());
		if (null == userModel.getFirstName())
			throw new NOT_FOUND("Please enter firstname");
		if (!dataValidation.isValidate(userModel.getFirstName(), DataValidation.FIRSTNAME_PATTERN))
			throw new FIRSTNAME_PATTERN_NOT_MATCH(); {
			if (null == userModel.getEmailId())
				throw new NOT_FOUND("Please enter emailId");
			if (!dataValidation.isValidate(userModel.getEmailId(), DataValidation.EMAIL_PATTERN))
				throw new EMAILID_PATTERN_NOT_MATCH();
		}
		return true;
	}

	public boolean validateDobAndGender(String dob, int genderId) throws ParseException {
		if (!DateUtility.isThisDateValid(dob, DateUtility.DATE_FORMAT_DD_MM_YYYY))
			throw new NOT_FOUND("Date format should be " + DateUtility.DATE_FORMAT_DD_MM_YYYY);
		else {
			if (!DateUtility.dobValidation(dob))
				throw new NOT_FOUND("Date of birth is below 18 years " + dob);
		}
		if (0 == genderId)
			throw new NOT_FOUND("Please enter gender");
		return true;
	}
	


	public void validate(UserModel userModel, String id, boolean status) throws Exception {
		
		if (userModel.getProfileSource().equalsIgnoreCase(Constants.PROFILE_SOURCE))		
		{
			if (null == userModel.getFirstName() || userModel.getFirstName().isEmpty())
				throw new VALIDATE("Please Enter the Name!!");
			if (!dataValidation.isValidate(userModel.getFirstName(), DataValidation.FIRSTNAME_PATTERN))
				throw new VALIDATE("Please Enter the Proper Name!!");
			
		AddressModel addressModel = userModel.getAddressModel();
		if (null == addressModel.getCountry())
			throw new VALIDATE("Please mention the country");
		if (null == addressModel.getStateId())
			throw new VALIDATE("Please mention the state");
		if (null == addressModel.getCityId())
			throw new VALIDATE("Please mention the city");
		if (null == addressModel.getPinCode())
			throw new VALIDATE("Please mention PIN Code");
		if (null == addressModel.getAddress1())
			throw new VALIDATE("Please Enter the Address");

		List<BankModel> bankModel = userModel.getBankModel();
		for (BankModel bank : bankModel) {
			if (bank.getAccountNumber() != null) {
				if (bank.getAccountNumber().toString().length() < 9 || bank.getAccountNumber().toString().length() > 21)
					throw new VALIDATE("AccountNumber length should be within 9-21 digits");
				accountNumber(bank.getAccountNumber(), id, status);
			} else
				throw new NOT_FOUND("Please enter the account number");
			if (!Integer.toString(userModel.getRoleId()).equalsIgnoreCase(Role.PURCHASER.getCode())) {
				if (!Integer.toString(userModel.getRoleId()).equalsIgnoreCase(Role.STUDENT.getCode())) {
						
				if (bank.getPanNumber() != null && bank.getPanNumber().trim().length() > 0)
					panNumber(bank.getPanNumber(), id, status);
				else
					throw new NOT_FOUND("Please enter the pan number");
			}
			if (null == bank.getBranchName())
				throw new NOT_FOUND("Please mention the branch Name");
			if (null == bank.getIfscCode())
				throw new NOT_FOUND("Please mention the ifsccode");
			if (0 == bank.getAdminBankId())
				throw new NOT_FOUND("Please mention the bank");
		}
		}}
		else {
			throw new NOT_FOUND("Profile Source Mismatch");
		}
			
	}

	public boolean accountNumber(BigInteger accountNumber, String id, boolean updateStatus) throws Exception {
		BankDomain profile = bankDAORepository.findByAccountNumber(accountNumber);
		if (null != profile) {
			if (updateStatus) {
				if (!id.equalsIgnoreCase(profile.getUserId()))
					throw new ACCOUNT_NUMBER_ALREADY_EXIST();
			} else
				throw new ACCOUNT_NUMBER_ALREADY_EXIST();
		}
		return true;
	}

	public boolean panNumber(String panNumber, String id, boolean updateStatus) throws Exception {
		BankDomain profile = bankDAORepository.findByPanNumber(panNumber);
		if (null != profile) {
			if (updateStatus) {
				if (!id.equalsIgnoreCase(profile.getUserId()))
					throw new PAN_NUMBER_ALREADY_EXIST();
			} else
				throw new PAN_NUMBER_ALREADY_EXIST();
		}
		return true;
	}

	public void validatePassword(UserModel userModel) {
		if (!dataValidation.isValidate(userModel.getPassword(), DataValidation.PASSWORD_PATTERN))
			throw new PASSWORD_PATTERN_NOT_MATCHED();
		if (!userModel.getPassword().equals(userModel.getConfirmPassword()))
			throw new PASSWORD_MISMATCH();
		encryptPwd(userModel);
	}

	private boolean encryptPwd(UserModel userModel) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(userModel.getPassword());
		userModel.setPassword(password);
		userModel.setConfirmPassword(password);
		return true;
	}

	public UserDomain isUserExistsByMobileOrEmail(UserModel userModel) throws Exception {
		UserDomain userDomain = null;
		if ((null == userModel.getEmailId() || userModel.getEmailId().isEmpty())
				|| (0 != userModel.getMobileNumber())) {
			userDomain = userDAORepository.findByMobileNumberOrEmailIdAndRoleDomain(userModel.getMobileNumber(), userModel.getEmailId(),
					userModel.getRoleId());
			if (null != userDomain) {
				if (!userDomain.isStatus()) {
					throw new USER_ALREADY_EXIST(userModel.getMobileNumber(), false);
				} else
					throw new USER_ALREADY_EXIST(userModel.getMobileNumber());
			}
		} else
			isEmailIdExists(userModel.getEmailId());
		return userDomain;
	}

	public boolean isEmailIdExists(String emailId) throws Exception {
		if (emailId == null)
			throw new VALIDATE("Please Enter EmailId");
		UserDomain userDomain = userDAORepository.findByEmailId(emailId);
		if (userDomain == null)
			return true;
		else
			throw new USER_ALREADY_EXIST();
	}


	public UserModel saveUserRole(UserModel userModel, UserDomain userDomain) throws Exception {
		UserRoleDomain userRoleDomain = new UserRoleDomain();
		UserDomain userD = new UserDomain();
		RoleDomain roleDomain = new RoleDomain();
		List<UserDomain> userByMobileNoOrEmailList = userDAORepository.findByMobileNumberOrEmailId(userModel.getMobileNumber(),
				userModel.getEmailId());
//		if (userModel.getRoleId() != Integer.parseInt(Role.CUSTOMER.getCode()))
//			userModel.setStatus(false);
//		else
			
			userModel.setStatus(true);
		if ((null == userDomain.getId() || userDomain.getId().isEmpty()) && userByMobileNoOrEmailList.isEmpty()) {
			userModel.setId(idGeneration.generateRandomId());
			BeanUtils.copyProperties(userModel, userD);
			if (userModel.getRoleId() == Integer.parseInt(Role.EMPLOYEE.getCode())) {
				roleDomain.setRoleId(userModel.getEmployeeRoleId());
			} else {
				roleDomain.setRoleId(userModel.getRoleId());
			}
			userD.setRoleDomain(roleDomain);
			userD = userDAORepository.save(userD);
			BeanUtils.copyProperties(userD, userRoleDomain);
		} else {
			if (!userByMobileNoOrEmailList.isEmpty()) {
				BeanUtils.copyProperties(userByMobileNoOrEmailList.get(0), userD);
			} else
				BeanUtils.copyProperties(userD, userDomain);
			userD.setStatus(userModel.isStatus());
		}
		userRoleDomain.setUuId(idGeneration.generateRandomId());
		UserDomain userdomain = new UserDomain();
		userdomain.setId(userD.getId());
		userRoleDomain.setUserDomain(userdomain);
		if (userModel.getRoleId() == Integer.parseInt(Role.EMPLOYEE.getCode())) {
			roleDomain.setRoleId(userModel.getEmployeeRoleId());
		} else {
			roleDomain.setRoleId(userModel.getRoleId());
		}
		userRoleDomain.setRoleDomain(roleDomain);
		userRoleDomain.setActive(userD.isStatus());
		userRoleDomain.setStatus(true);
		userRoleDaoRepository.save(userRoleDomain);
		UserModel userId = new UserModel();
		userId.setId(userRoleDomain.getUserDomain().getId());
		if (!userByMobileNoOrEmailList.isEmpty())
			userId.setWarningMessage("User already Registered in this role : "
					+ Role.getRole(userByMobileNoOrEmailList.get(0).getRoleDomain().getRoleId() + "") + ", New role:"
					+ Role.getRole(userModel.getRoleId() + "") + " also assigned");
		else
			userId.setWarningMessage(null);
		return userId;
	}
}
