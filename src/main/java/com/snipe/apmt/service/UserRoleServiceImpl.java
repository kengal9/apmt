package com.snipe.apmt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.snipe.apmt.dao.RoleRepository;
import com.snipe.apmt.dao.UserRoleDaoRepository;
import com.snipe.apmt.domain.UserRoleDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.mapper.UserRoleMapper;
import com.snipe.apmt.model.UserRoleModel;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRoleDaoRepository userRoleDaoRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Override
	public String addUserRole(UserRoleModel userRoleModel) throws Exception {
		UserRoleDomain userRoleDomain = new UserRoleDomain();
		BeanUtils.copyProperties(userRoleModel, userRoleDomain);
		try {
			userRoleDaoRepository.save(userRoleDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in UserRoleService: ", e.getMessage());

		}
		return "saved";
	}

	@Override
	public List<UserRoleDomain> getUserRoleByUserID(String userId) throws Exception {
		try {
			List<UserRoleDomain> userRoleDomain = UserRoleDaoRepository.getUserRoleByUserDomain(userId);
			return userRoleDomain;
		} catch (Exception e) {
			logger.error("Exception created in UserRoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public UserRoleModel userRoleCount(String userId) throws Exception {
		try {
			UserRoleModel userRoleModel = UserRoleDaoRepository.userRoleCount(userId);
			return userRoleModel;
		} catch (Exception e) {
			logger.error("Exception created in UserRoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public List<UserRoleModel> getUserRoleList() throws Exception {
		return null;
	}

	@Override
	public String updateUserRole(UserRoleModel userRoleModel) throws Exception {
		return null;
	}

	@Override
	public String deleteUserRoleById(String uuId) throws Exception {
		try {
			return userRoleDaoRepository.deleteUserRoleByUuId(uuId);
		} catch (Exception e) {
			logger.error("Exception created in UserRoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

}
