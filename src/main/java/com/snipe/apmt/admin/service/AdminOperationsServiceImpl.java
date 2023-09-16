package com.snipe.apmt.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.AdminOperationsDAORepository;
import com.snipe.apmt.admin.controller.AdminOperationsController;
import com.snipe.apmt.common.CommonApi;
import com.snipe.apmt.dao.UserLogRepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.UserLogDomain;
import com.snipe.apmt.exception.APMTRestException.USER_NOT_FOUND;
import com.snipe.apmt.model.UserLogModel;
import com.snipe.apmt.model.UserModel;

@Service("adminOperService")
public class AdminOperationsServiceImpl implements AdminOperationsService {
	private static Logger log = LoggerFactory.getLogger(AdminOperationsController.class);

	@Autowired
	AdminOperationsDAORepository adminOperDAORepository;

	@Autowired
	CommonApi commonApi;

	@Autowired
	UserLogRepository userLogRepository;
	
	@Override
	public List<UserModel> getExternalUsersList() throws Exception {

		List<UserDomain> userDomainList = adminOperDAORepository.getListOfExternalUsers();
		return populateProperties(userDomainList);
	}

	@Override
	public List<UserModel> getInternalUsersList()throws Exception  {
		log.info("Inside service method of getInternalUserList before populate");
		List<UserDomain> userDomainList = adminOperDAORepository.getListOfInternalUsers();
		
			return populateProperties(userDomainList);
		
		
		
	}

	private List<UserModel> populateProperties(List<UserDomain> userDomainList) throws Exception {
		List<UserModel> userModelList = new ArrayList<UserModel>();

		Optional<List<UserDomain>> checkNull = Optional.ofNullable(userDomainList);
		if (checkNull.isPresent()) {
			for (UserDomain userDomain : userDomainList) {
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(userDomain, userModel);
				if (userDomain.getAddressDomain() != null && userDomain.getBankDomain() != null) {
					userModel.setCountry(commonApi.getCountryName(userDomain.getAddressDomain().getCountryId()));
					userModel.setState(commonApi.getStateName(userDomain.getAddressDomain().getStateId()));
					userModel.setCity(commonApi.getCityName(userDomain.getAddressDomain().getCityId()));
				}
				log.info("Inside populateProperties method ");
				userModel.setRoleId(userDomain.getRoleDomain().getRoleId());
				log.info("Inside populateProperties method ");
				userModelList.add(userModel);

			}

		}
		return userModelList;
	}

	@Override
	public UserModel updateUserStatus(String id, boolean status) {
		log.info("Inside update user status");
		UserDomain userDomain = adminOperDAORepository.findById(id).orElseThrow(()-> new USER_NOT_FOUND());
		UserModel userModel = new UserModel();
		userDomain.setStatus(status);
		adminOperDAORepository.save(userDomain);
		log.info("After updating user status");
		BeanUtils.copyProperties(userDomain, userModel);
		 return userModel;
	}

	@Override
	public String getIdForUser(UserModel userModel) {
		UserDomain userDomain = adminOperDAORepository.getIdForUser(userModel.getFirstName(),userModel.getAadharNumber(),userModel.getEmailId());
		return userDomain.getId();
	}

	@Override
	public List<UserLogModel> getActivityDetails(String roleName) {
		log.info("Inside getActivity details");
		List<UserLogDomain> userLogDomainList = userLogRepository.getActivityDetails(roleName);
		List<UserLogModel> userLogModelList = new ArrayList<UserLogModel>();;
		Optional<List<UserLogDomain>> checkNull = Optional.ofNullable(userLogDomainList);
		if (checkNull.isPresent()) {
			for (UserLogDomain userLogDomain : userLogDomainList) {
				UserLogModel userLogModel = new UserLogModel();
				BeanUtils.copyProperties(userLogDomain, userLogModel);
				userLogModelList.add(userLogModel);
			}
			log.info("Inside getActivity details after for loop");


		}
		return userLogModelList;
	}
}
