package com.snipe.apmt.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.snipe.apmt.admin.DAO.CountryRepository;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.mapper.CountryMapper;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;

@Service
public class CountryServiceImpl implements CountryService {


	@Autowired
	CountryMapper countryMapper;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Override
	public String addCountry(CountryModel countryModel) throws Exception {
		CountryDomain countryDomain = new CountryDomain();
		BeanUtils.copyProperties(countryModel, countryDomain);
		try {
		countryRepository.save(countryDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in CountryService: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
			
		}
		return "country saved";
	}

	@Override
	public List<CountryModel> getCountryList() throws Exception {
		try {
		List<CountryDomain> countryDomain = countryRepository.findAll();
		return countryMapper.entityList(countryDomain);
		}
		catch (Exception e) {
			logger.error("Exception create in CountryServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public CountryModel getCountryListById(String countryId) throws Exception {
		CountryModel countryModel = new CountryModel();
		try {
		CountryDomain countryDomain = countryRepository.findByCountryId(countryId);
		BeanUtils.copyProperties(countryDomain, countryModel);
		}
		catch (Exception e) {
			logger.error("Exception create in CountryServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		return countryModel;
	}

	@Override
	public String updateCountry(CountryModel countryModel) throws Exception {
		CountryDomain countryDomain = new CountryDomain();
		BeanUtils.copyProperties(countryModel, countryDomain);
		try {
		countryRepository.save(countryDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in CountryServiceImpl: ", e.getMessage());
			
		}
		
		return "country updated";
	}

	public int deleteCountryById(String countryId) throws Exception {
		try {
		return countryRepository.deleteByCountryId(countryId);
		}
		catch (Exception e) {
			logger.error("Exception created in CountryRoleServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	

}
