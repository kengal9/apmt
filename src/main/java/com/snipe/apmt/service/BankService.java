package com.snipe.apmt.service;

import java.math.BigInteger;
import java.util.List;

import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.model.UserModel;

public interface BankService {

	public BankDomain saveBankInfo(UserModel userModel) throws Exception;

	public BankDomain isAccountNumberExists(BigInteger accountNumber) throws Exception;

	public String deleteBankById(String bankId) throws Exception;

	public BankDomain getBankById(String bankId) throws Exception;

	public BankDomain getBankByUserId(String userId) throws Exception;

	
}
