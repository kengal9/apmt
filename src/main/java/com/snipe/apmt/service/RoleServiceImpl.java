package com.snipe.apmt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.snipe.apmt.dao.RoleRepository;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.mapper.RoleMapper;
import com.snipe.apmt.model.RoleModel;

@Service
public class RoleServiceImpl implements RoleService {

	

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	RoleRepository roleRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public String addRole(RoleModel roleModel) throws Exception {
		RoleDomain roleDomain = new RoleDomain();
		BeanUtils.copyProperties(roleModel, roleDomain);
		try {
		roleRepository.save(roleDomain);
		}
		 catch (DataIntegrityViolationException e) {

				throw new DUPLIACATE_ENTRY_FOUND();
			} catch (Exception e) {
				logger.error("Exception created in RoleService: ", e.getMessage());

			}
		return "Role saved";
	}

	@Override
	public RoleModel getRole(int roleId) throws Exception {
		RoleDomain roleDomain =new RoleDomain();
		try {
		roleDomain = roleRepository.findByRoleId(roleId);
		}
		catch (Exception e) {
			logger.error("Exception created in RoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		RoleModel roleModel = new RoleModel();
		BeanUtils.copyProperties(roleDomain, roleModel);
		return roleModel;
	}

	@Override
	public RoleModel roleCount() throws Exception {
		RoleModel roleModel = new RoleModel();
		try {
		roleModel.setCount(roleRepository.count());
		}
		catch (Exception e) {
			logger.error("Exception created in RoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return roleModel;
	}

	@Override
	public List<RoleModel> getRoleList() throws Exception {
		List<RoleDomain> roleDomain = new ArrayList<RoleDomain>();
		try {
		roleDomain = roleRepository.findAll();
		}
		catch (Exception e) {
			logger.error("Exception created in RoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return roleMapper.entityList(roleDomain);
	}

	@Override
	public String updateRole(RoleModel roleModel) throws Exception {
		RoleDomain roleDomain = new RoleDomain();
		BeanUtils.copyProperties(roleModel, roleDomain);
		try {
		roleRepository.save(roleDomain);
		}
		 catch (DataIntegrityViolationException e) {

				throw new DUPLIACATE_ENTRY_FOUND();
			} catch (Exception e) {
				logger.error("Exception created in RoleService: ", e.getMessage());

			}
		return "Role updated";

	}

	@Override
	public int deleteRoleById(int roleId) throws Exception {
		try {
			
		return roleRepository.deleteByRoleId(roleId);
		}
		catch (Exception e) {
			logger.error("Exception created in RoleServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		
	}

}
