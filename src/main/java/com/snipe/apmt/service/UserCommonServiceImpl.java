package com.snipe.apmt.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Role;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.exception.APMTRestException.ROLE_NOT_FOUND;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.utils.UserUtils;

@Transactional
@Service("userCommonService")
//@Qualifier("userCommonService")
public class UserCommonServiceImpl implements UserCommonService {

	@Autowired
	UserUtils userUtils;

	@Autowired
	UserDAORepository userDAORepository;
	

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	BankService bankService;

	@Autowired
	AddressService addressService;

//	@Autowired
//	AgentService agentService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserCommonServiceImpl.class);

	@Override
	public UserModel save(UserModel userModel, UserDomain userDomain) throws Exception {
		UserModel user = new UserModel();
		UserModel userM = new UserModel();
		Role role = Role.getRole(userModel.getRoleId() + "");
		if (null == role)
			throw new ROLE_NOT_FOUND(userModel.getRoleId() + "");
		switch (role) {
		case ADMIN:
		case VERIFICATION_MANAGER:
		case SALES_MANAGER:
		case EMPLOYEE:
			user = userUtils.saveUserRole(userModel, userDomain);
			BankDomain bankDomain = bankService.saveBankInfo(userModel);
			AddressDomain addressDomain = addressService.saveAddressInfo(userModel);
			userModel.setBankId(bankDomain.getBankId());
			userModel.setAddressId(addressDomain.getAddressId());
			//userDAORepository.updateBank(userModel);
			try {
			userDAORepository.updateUserBankId(userModel.getBankId(),userModel.getId());
			}
			catch (DataIntegrityViolationException e) {

				throw new DUPLIACATE_ENTRY_FOUND();
			} catch (Exception e) {
				logger.error("Exception created in UserCommonService: ", e.getMessage());

			}
			//userDAORepository.updateAddress(userModel);
			try {
			userDAORepository.updateUserAddressId(userModel.getAddressId(),userModel.getId());
			}
			catch (DataIntegrityViolationException e) {

				throw new DUPLIACATE_ENTRY_FOUND();
			} catch (Exception e) {
				logger.error("Exception created in UserCommonService: ", e.getMessage());

			}
			BeanUtils.copyProperties(user, userM);
			break;
		case STUDENT:
		case PURCHASER:
			user = userUtils.saveUserRole(userModel, userDomain);
			AddressDomain addressDomain1 = addressService.saveAddressInfo(userModel);
			userModel.setAddressId(addressDomain1.getAddressId());
			try {
			userDAORepository.updateUserAddressId(userModel.getAddressId(),userModel.getId());
			}
			catch (DataIntegrityViolationException e) {

				throw new DUPLIACATE_ENTRY_FOUND();
			} catch (Exception e) {
				logger.error("Exception created in UserCommonService: ", e.getMessage());

			}
			BeanUtils.copyProperties(user, userM);
			break;
		default:
			break;
		}
		
		return userM;
	}

	@Override
	public String update(UserModel userModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
