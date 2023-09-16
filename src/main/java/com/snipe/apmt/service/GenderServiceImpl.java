package com.snipe.apmt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.dao.GenderRepository;
import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.mapper.GenderMapper;
import com.snipe.apmt.model.GenderModel;
import com.snipe.apmt.model.ProjectModel;
import com.snipe.apmt.utils.CommonUtils;

@Service
@Transactional
public class GenderServiceImpl implements GenderService {

	@Autowired
	GenderRepository genderRepository;

	@Autowired
	GenderMapper genderMapper;

	private static final Logger logger = LoggerFactory.getLogger(GenderServiceImpl.class);

	@Override
	public String saveGender(GenderModel genderModel) throws Exception {
		GenderDomain genderDomain = new GenderDomain();
		// genderModel.setGenderId(idGeneration.generateRandomId());
		BeanUtils.copyProperties(genderModel, genderDomain);
		try {
			genderRepository.save(genderDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in GenderService: ", e.getMessage());

		}
		return "Gender Saved";
	}

	@Override
	public List<GenderModel> getGenderList() throws Exception {
		try {
		List<GenderDomain> genderDomain = genderRepository.findAll();
		return genderMapper.entityList(genderDomain);
		}
		catch (Exception e) {
			logger.error("Exception created in GenderServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();

		}
	}

	@Override
	public GenderModel getGenderById(String genderId) {
		GenderModel genderModel = new GenderModel();
		GenderDomain genderDomain =new GenderDomain();
		try {
		genderDomain = genderRepository.findByGenderId(genderId);
		}
		catch (Exception e) {
			logger.error("Exception created in GenderServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		BeanUtils.copyProperties(genderDomain, genderModel);
		return genderModel;
	}

	@Override
	public String updateGender(GenderModel genderModel) throws Exception {
		GenderDomain genderDomain = new GenderDomain();
		BeanUtils.copyProperties(genderModel, genderDomain);
		try {
			genderRepository.save(genderDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in AdminBankService: ", e.getMessage());

		}
		return "Gender updated";
	}

	@Override
	public String deleteByGenderId(String genderId) throws Exception {
		try {
		genderRepository.deleteByGenderId(genderId);
		}
		catch (Exception e) {
			logger.error("Exception created in GenderServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return "Gender Deleted";
	}
}
