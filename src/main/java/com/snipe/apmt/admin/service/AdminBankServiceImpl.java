package com.snipe.apmt.admin.service;

import java.util.List;

import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.utils.CommonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.AdminBankRepository;
import com.snipe.apmt.admin.domain.AdminBankDomain;
import com.snipe.apmt.admin.mapper.AdminBankMapper;
import com.snipe.apmt.admin.model.AdminBankModel;

@Service
public class AdminBankServiceImpl implements AdminBankService {

	@Autowired
	AdminBankRepository adminBankRepository;

	@Autowired
	AdminBankMapper adminBankMapper;

	private static final Logger logger = LoggerFactory.getLogger(AdminBankServiceImpl.class);

	@Override
	public String adminAddBank(AdminBankModel adminBankModel) throws Exception {
		AdminBankDomain adminBankDomain = new AdminBankDomain();
		BeanUtils.copyProperties(adminBankModel, adminBankDomain);
		try {
			adminBankRepository.save(adminBankDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in AdminBankService: ", e.getMessage());

		}
		return "Bank saved";
	}

	@Override
	public List<AdminBankModel> getAdminBankList() throws Exception {
		try {
			List<AdminBankDomain> adminBankDomain = adminBankRepository.findAll();
			return adminBankMapper.entityList(adminBankDomain);
		} catch (Exception e) {
			logger.error("Exception created in AdminBankServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public AdminBankModel getAdminBankListById(String adminBankId) throws Exception {
		AdminBankModel adminBankModel = new AdminBankModel();
		try {
			AdminBankDomain adminBankDomain = adminBankRepository.findByAdminBankId(adminBankId);
			BeanUtils.copyProperties(adminBankDomain, adminBankModel);
		} catch (Exception e) {
			logger.error("Exception created in AdminBankServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return adminBankModel;
	}

//	@Override
//	public AdminBankModel getAdminBankListByname(String adminBankName) throws Exception {
//		AdminBankModel adminBankModel = new AdminBankModel();
//		AdminBankDomain adminBankDomain = adminBankRepository.findByAdminBankName(adminBankName);
//		BeanUtils.copyProperties(adminBankDomain, adminBankModel);
//		return adminBankModel;
//	}

	@Override
	public String adminUpdateBank(AdminBankModel adminBankModel) throws Exception {
		AdminBankDomain adminBankDomain = new AdminBankDomain();
		BeanUtils.copyProperties(adminBankModel, adminBankDomain);
		try {
		adminBankRepository.save(adminBankDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in AdminBankService: ", e.getMessage());

		}
		
		return "updated";

	}

	public int adminDeleteBankById(String adminBankId) throws Exception {
		try {
		return adminBankRepository.deleteByAdminBankId(adminBankId);
		}
		catch (Exception e) {
			logger.error("Exception created in AdminBankServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

	}

	public long adminBankCount() throws Exception {
		AdminBankModel adminBankModel = new AdminBankModel();
		try {
		adminBankModel.setCount(adminBankRepository.count());
		}
		catch (Exception e) {
			logger.error("Exception created in AdminBankServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return adminBankModel.getCount();

	}

}
