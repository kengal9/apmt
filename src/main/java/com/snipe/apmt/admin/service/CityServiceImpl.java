package com.snipe.apmt.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.snipe.apmt.admin.DAO.CityRepository;
import com.snipe.apmt.admin.DAO.StateRepository;
import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.admin.mapper.CityMapper;
import com.snipe.apmt.admin.model.CityModel;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;

@Service
public class CityServiceImpl implements CityService {


	@Autowired
	CityMapper cityMapper;

	@Autowired
	CityRepository cityRepository;

	
	@Autowired
	StateRepository stateRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Override
	public String addCity(CityModel cityModel) throws Exception {
		CityDomain cityDomain = new CityDomain();
		BeanUtils.copyProperties(cityModel, cityDomain);
		try {
		cityRepository.save(cityDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in CityService: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
			
		}
		return "city saved";
	}

	@Override
	public List<CityDomain> getCityList() throws Exception {
		try {
		List<CityDomain> cityDomain = cityRepository.findAll();
		return cityDomain;
		}
		catch (Exception e) {
			logger.error("Exception create in CityServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public CityDomain getCityListById(String cityId) throws Exception {
		try {
		CityDomain cityDomain = cityRepository.findByCityId(cityId);
		return cityDomain;
		}
		catch (Exception e) {
			logger.error("Exception created in CityServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public String updateCity(CityModel cityModel) throws Exception {
		CityDomain cityDomain = new CityDomain();
		BeanUtils.copyProperties(cityModel, cityDomain);
		try {
		cityRepository.save(cityDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in CityService: ", e.getMessage());
			
		}
		return "city updated";

	}

	@Override
	public int deleteCityById(String cityId) throws Exception {
		try {
		return cityRepository.deleteByCityId(cityId);
		}
		catch (Exception e) {
			logger.error("Exception created in CityServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		
	}


	@Override
	public List<CityDomain> getCityList(String stateId) throws Exception {
		try {
		List<CityDomain> cityDomain = cityRepository.findByStateDomain(stateId);
		return cityDomain;
		}
		catch (Exception e) {
			logger.error("Exception created in CityServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}
}
