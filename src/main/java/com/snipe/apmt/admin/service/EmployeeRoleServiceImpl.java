package com.snipe.apmt.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.EmployeeRoleRepository;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.domain.EmployeeRoleDomain;
import com.snipe.apmt.admin.mapper.EmployeeRoleMapper;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.admin.model.EmployeeRoleModel;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {
	
	

	@Autowired
	EmployeeRoleRepository employeeRoleRepository;
	
	@Autowired
	EmployeeRoleMapper employeeRoleMapper;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRoleServiceImpl.class);

	@Override
	public String addEmployeeRole(EmployeeRoleModel employeeRoleModel) throws Exception {
		EmployeeRoleDomain employeeRoleDomain = new EmployeeRoleDomain();
		BeanUtils.copyProperties(employeeRoleModel, employeeRoleDomain);
		try {
		employeeRoleRepository.save(employeeRoleDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in EmployeeRoleService: ", e.getMessage());
			
		}
		return "EmployeeRole saved";
}

	

	@Override
	public List<EmployeeRoleModel> getEmployeeRoleList() throws Exception {
		try {
		List<EmployeeRoleDomain> employeeRoleDomain = employeeRoleRepository.findAll();
		return employeeRoleMapper.entityList(employeeRoleDomain);
		}
		catch (Exception e) {
			logger.error("Exception create in EmployeeRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public EmployeeRoleModel getEmployeeRoleListById(String employeeRoleId) throws Exception {
		EmployeeRoleModel employeeRoleModel = new EmployeeRoleModel();
		try {
		EmployeeRoleDomain employeeRoleDomain = employeeRoleRepository.findByEmployeeRoleId(employeeRoleId);
		BeanUtils.copyProperties(employeeRoleDomain, employeeRoleModel);
		}
		catch (Exception e) {
			logger.error("Exception create in EmployeeRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		
		return employeeRoleModel;
	}

	@Override
	public String updateEmployeeRole(EmployeeRoleModel employeeRoleModel) throws Exception {
		EmployeeRoleDomain employeeRoleDomain = new EmployeeRoleDomain();
		BeanUtils.copyProperties(employeeRoleModel, employeeRoleDomain);
		try {
		employeeRoleRepository.save(employeeRoleDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in EmployeeRoleService: ", e.getMessage());
			
		}
		return "EmployeeRole updated";
	}

	public int deleteEmployeeRoleById(String employeeRoleId) throws Exception {
		try {
		return employeeRoleRepository.deleteEmployeeRoleById(employeeRoleId);
		}
		catch (Exception e) {
			logger.error("Exception created in EmployeeRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}
}
