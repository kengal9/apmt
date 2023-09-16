package com.snipe.apmt.service;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.snipe.apmt.common.APMTEnum;
import com.snipe.apmt.common.ApiUtility;
import com.snipe.apmt.common.CommonApi;
import com.snipe.apmt.common.GenericHttpClient;
import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Role;
import com.snipe.apmt.dao.AddressDAORepository;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.APMTRestException.AGENT_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.BANKAGENT_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.exception.APMTRestException.INFRASTRUCTURE_CONTRACTS_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.INFRASTRUCTURE_MATERIALS_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.INFRASTRUCTURE_SERVICES_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.LAWYER_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.SUBREGISTERAGENT_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.VALIDATE;
import com.snipe.apmt.filter.JwtToken;
import com.snipe.apmt.model.AddressModel;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.utils.DataValidation;
import com.snipe.apmt.utils.IDGeneration;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	DataValidation dataValidation;

	@Autowired
	CommonApi commonApi;

	@Autowired
	AddressDAORepository addressDAORepository;

	@Autowired
	UserDAORepository userDAORepository;

	@Autowired
	IDGeneration idGeneration;

	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Override
	public AddressDomain saveAddressInfo(UserModel userModel) throws Exception {
		AddressModel addressModel = userModel.getAddressModel();
		AddressDomain addressDomain = new AddressDomain();
		BeanUtils.copyProperties(addressModel, addressDomain);
		addressDomain.setAddressId(idGeneration.generateRandomId());
		addressDomain.setUserId(userModel.getId());
		if (userModel.getRoleId() == Integer.parseInt(Role.EMPLOYEE.getCode())) {
			addressDomain.setRoleId(userModel.getEmployeeRoleId());
		} else {
			addressDomain.setRoleId(userModel.getRoleId());
		}
//		addressDomainV1.setRoleId(userModel.getRoleId());
		addressDomain.setStatus(true);
		try {
			addressDomain = addressDAORepository.save(addressDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in AddressServiceImpl: ", e.getMessage());

		}
		return addressDomain;
	}

	@Override
	public String deleteAddressById(String addressId) throws Exception {
		if (addressId == null)
			throw new VALIDATE("Please Mention addressId to Delete Address Info");
		try {
			addressDAORepository.deleteByAddressId(addressId);
		} catch (Exception e) {
			logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}

		return "deleted";
	}

	@Override
	public List<AddressModel> getAddressByStateId(String stateId, int roleId, boolean status) throws Exception {
		List<AddressDomain> addressDomain = new ArrayList<AddressDomain>();
		UserDomain userDomain = new UserDomain();
		if (stateId == null || roleId == 0)
			throw new VALIDATE("Please Mention StateId and RoleId to get Address Info");
		Role role = Role.getRole(roleId + "");
		try {
			addressDomain = addressDAORepository.findByStateIdAndRoleId(stateId, roleId);
		} catch (Exception e) {
			logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		List<AddressModel> addressModel = new ArrayList<AddressModel>();

		for (AddressDomain address : addressDomain) {
			AddressModel addressM = new AddressModel();
			try {
				userDomain = userDAORepository.findByIdOrStatus(addressM.getUserId(), status);
			} catch (Exception e) {
				logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
				throw new BACKEND_SERVER_ERROR();

			}
			if (userDomain == null) {
				continue;

			} else {
				addressM.setCountry(commonApi.getCountryName(userDomain.getAddressDomain().getCountryId()));
				addressM.setState(commonApi.getStateName(userDomain.getAddressDomain().getStateId()));
				addressM.setCity(commonApi.getCityName(userDomain.getAddressDomain().getCityId()));
				addressM.setMobileNumber(userDomain.getMobileNumber());
				addressM.setUserId(userDomain.getId());
				// addressM.setEmailId(userDomain.getEmailId());
			}

			addressModel.add(addressM);

		}

		if (addressModel == null || addressModel.isEmpty())
			throw new NOT_FOUND(null);

		return addressModel;

	}

	// @Override
	public List<AddressModel> getAddressByCityId(String cityId, int roleId, boolean status) throws Exception {
		UserDomain userDomain = new UserDomain();
		List<AddressDomain> addressDomain=new ArrayList<AddressDomain>();
		if (cityId == null || roleId == 0)
			throw new VALIDATE("Please Mention CityId and RoleId to get Address Info");
		Role role = Role.getRole(roleId + "");

		try {
		addressDomain = addressDAORepository.findByCityIdOrRoleId(cityId, roleId);
		}
		catch (Exception e) {
			logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		List<AddressModel> addressModel = new ArrayList<AddressModel>();

		for (AddressDomain address : addressDomain) {
			AddressModel addressM = new AddressModel();
			try {
				userDomain = userDAORepository.findByIdOrStatus(address.getUserId(), status);
			} catch (Exception e) {
				logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
				throw new BACKEND_SERVER_ERROR();

			}

			if (userDomain == null) {
				continue;
			} else {
				addressM.setCountry(commonApi.getCountryName(userDomain.getAddressDomain().getCountryId()));
				addressM.setState(commonApi.getStateName(userDomain.getAddressDomain().getStateId()));
				addressM.setCity(commonApi.getCityName(userDomain.getAddressDomain().getCityId()));
				addressM.setMobileNumber(userDomain.getMobileNumber());
				addressM.setUserId(userDomain.getId());
				// addressM.setEmailId(userDomain.getEmailId());
				addressModel.add(addressM);
			}
		}
		if (addressModel == null || addressModel.isEmpty())
			throw new NOT_FOUND(null);
		return addressModel;
	}

	@Override
	public List<AddressModel> getAddressByUserId(String userId, int roleId) throws Exception {
		List<AddressDomain> addressDomain = new ArrayList<AddressDomain>();
		try {
			addressDomain = addressDAORepository.findByUserIdOrRoleId(userId, roleId);
		} catch (Exception e) {
			logger.error("Exception created in AddressServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
		List<AddressModel> addressModel = new ArrayList<AddressModel>();
		for (AddressDomain adreesDetails : addressDomain) {
			AddressModel addressModelList = new AddressModel();
			BeanUtils.copyProperties(adreesDetails, addressModelList);
			addressModel.add(addressModelList);
		}
		return addressModel;
	}

}
