package com.snipe.apmt.service;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Role;
import com.snipe.apmt.dao.BankDAORepository;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.VALIDATE;
import com.snipe.apmt.filter.JwtToken;
import com.snipe.apmt.mapper.BankMapper;
import com.snipe.apmt.model.BankModel;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.utils.DataValidation;
import com.snipe.apmt.utils.IDGeneration;

@Service
@SuppressWarnings("unused")
public class BankServiceImpl implements BankService {

	

	@Autowired
	BankMapper bankMapper;

	@Autowired
	BankDAORepository bankDAORepository;

	@Autowired
	DataValidation dataValidation;

	@Autowired
	JwtToken jwtToken;

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	IDGeneration idGeneration;
	
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Override
	public BankDomain saveBankInfo(UserModel userModel) throws Exception {
		BankDomain bankDomain = new BankDomain();
		List<BankModel> bankModel = userModel.getBankModel();
		for (BankModel bankModel1 : bankModel) {
			BeanUtils.copyProperties(bankModel1, bankDomain);
			bankDomain.setBankId(idGeneration.generateRandomId());
			bankDomain.setUserId(userModel.getId());
			if (userModel.getRoleId() == Integer.parseInt(Role.EMPLOYEE.getCode())) {
				bankDomain.setRoleId(userModel.getEmployeeRoleId());
			} else {
				bankDomain.setRoleId(userModel.getRoleId());
			}
			bankDomain.setStatus(true);
			try {
			bankDomain = bankDAORepository.save(bankDomain);
			}
			catch (Exception e) {
				logger.error("Exception created in BankServiceImpl: ", e.getMessage());

			}
		}
		return bankDomain;
	}


	@Override
	public String deleteBankById(String bankId) throws Exception {
		if (bankId == null)
			throw new VALIDATE("Please Mention bankId to Delete Bank Info");
		try {
		bankDAORepository.deleteByBankId(bankId);
		}
		catch (Exception e) {
			logger.error("Exception created in BankServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		return "deleted";
		
	}

	@Override
	public BankDomain getBankById(String bankId) throws Exception {
		BankModel bankModel = new BankModel();
		BankDomain bankDomain=new BankDomain();
		try {
		bankDomain = bankDAORepository.findByBankId(bankId);
		}
		catch (Exception e) {
			logger.error("Exception created in BankServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		
		BeanUtils.copyProperties(bankDomain, bankModel);
		return bankDomain;
	}
//	public BankDomain getBankById(String bankId) throws Exception {
//		return null;

	//}

	@Override
	public BankDomain getBankByUserId(String userId) throws Exception {
		BankDomain bankDomain=new BankDomain();
		if (userId == null)
			throw new VALIDATE("Please Mention userId to get Bank Info");
		try {
		bankDomain = bankDAORepository.findByUserId(userId);
		}
		catch (Exception e) {
			logger.error("Exception created in BankServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		return bankDomain;
	}

	@Override
	public BankDomain isAccountNumberExists(BigInteger accountNumber) throws Exception {
		try {
		BankDomain bankDomain = bankDAORepository.findByAccountNumber(accountNumber);
		return bankDomain;
		}
		catch (Exception e) {
			logger.error("Exception created in BankServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
	}


	

	

}
