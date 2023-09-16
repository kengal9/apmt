package com.snipe.apmt.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.BusinessRoleRepository;
import com.snipe.apmt.admin.domain.BusinessRoleDomain;
import com.snipe.apmt.admin.mapper.BusinessRoleMapper;
import com.snipe.apmt.admin.model.BusinessRoleModel;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;

@Service
public class BusinessRoleServiceImpl implements BusinessRoleService {

	@Autowired
	BusinessRoleRepository businessRoleRepository;

	@Autowired
	BusinessRoleMapper businessRoleMapper;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(BusinessRoleServiceImpl.class);

	@Override
	public String addBusinessRole(BusinessRoleModel businessRoleModel) throws Exception {
		BusinessRoleDomain businessRoleDomain = new BusinessRoleDomain();
		BeanUtils.copyProperties(businessRoleModel, businessRoleDomain);
		try {
			businessRoleRepository.save(businessRoleDomain);
		} 
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in BusinessRoleService: ", e.getMessage());
			
		}
		return "BusinessRole saved";
	}

	@Override
	public List<BusinessRoleModel> getBusinessRoleList() throws Exception {
		try {
		List<BusinessRoleDomain> businessRoleDomain = businessRoleRepository.findAll();
		return businessRoleMapper.entityList(businessRoleDomain);
		}
		catch (Exception e) {
			logger.error("Exception created in BusinessRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public BusinessRoleModel getBusinessRoleListById(String businessRoleId) throws Exception {
		BusinessRoleModel businessRoleModel = new BusinessRoleModel();
		try {
		BusinessRoleDomain businessRoleDomain = businessRoleRepository.findByBusinessRoleId(businessRoleId);
		BeanUtils.copyProperties(businessRoleDomain, businessRoleModel);
		}
		catch (Exception e) {
			logger.error("Exception created in BusinessRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		return businessRoleModel;
	}

	@Override
	public String updateBusinessRole(BusinessRoleModel businessRoleModel) throws Exception {
		BusinessRoleDomain businessRoleDomain = new BusinessRoleDomain();
		BeanUtils.copyProperties(businessRoleModel, businessRoleDomain);
		try {
		businessRoleRepository.save(businessRoleDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in BusinessRoleService: ", e.getMessage());
			
		}
		return "BusinessRole updated";
	}

	public int deleteBusinessRoleById(String businessRoleId) throws Exception {
		try {
		return businessRoleRepository.deleteBusinessRoleById(businessRoleId);
		}
		catch (Exception e) {
			logger.error("Exception created in BusinessRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}
}
