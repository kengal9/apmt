package com.snipe.apmt.common;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.snipe.apmt.admin.DAO.AdminBankRepository;
import com.snipe.apmt.admin.DAO.CityRepository;
import com.snipe.apmt.admin.DAO.CountryRepository;
import com.snipe.apmt.admin.DAO.StateRepository;
import com.snipe.apmt.admin.domain.AdminBankDomain;
import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.domain.StateDomain;
import com.snipe.apmt.admin.mapper.AdminBankMapper;
import com.snipe.apmt.admin.model.AdminBankModel;
import com.snipe.apmt.admin.model.CityModel;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.admin.model.StateModel;
import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.BANK_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.CITY_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.COUNTRY_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.HOBLI_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.STATE_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.TALUK_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.VILLAGE_NOT_FOUND;
import com.snipe.apmt.model.BankModel;

@Component
@SuppressWarnings("unused")
public class CommonApi {

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	AdminBankRepository adminBankRepository;

	public String getCountryName(String countryId) throws Exception {
		CountryDomain countryDomain = countryRepository.findByCountryId(countryId);
		CountryModel countryModel = new CountryModel();
		BeanUtils.copyProperties(countryDomain, countryModel);
		String countryname = countryModel.getCountryName();
		if (countryModel == null) {
			throw new COUNTRY_NOT_FOUND(countryId);
		} else {
			return countryname;
		}

	}

	public String getStateName(String stateId) throws Exception {
		StateDomain stateDomain = stateRepository.findByStateId(stateId);
		StateModel stateModel = new StateModel();
		BeanUtils.copyProperties(stateDomain, stateModel);
		String statename = stateModel.getStateName();
		if (stateModel == null) {
			throw new STATE_NOT_FOUND(stateId);
		} else {
			return statename;
		}
	}

	public String getCityName(String cityId) throws Exception {
		CityDomain cityDomain = cityRepository.findByCityId(cityId);
		CityModel cityModel = new CityModel();
		BeanUtils.copyProperties(cityDomain, cityModel);
		String cityname = cityModel.getCityName();
		if (cityModel == null) {
			throw new CITY_NOT_FOUND(cityId);
		} else {
			return cityname;
		}
	}

	public String getBankName(String adminBankId) throws Exception {
		AdminBankDomain adminBankDomain = adminBankRepository.findByAdminBankId(adminBankId);
		AdminBankModel AdminBankModel = new AdminBankModel();
		BeanUtils.copyProperties(adminBankDomain, AdminBankModel);
		String bankname = AdminBankModel.getAdminBankName();
		if (AdminBankModel == null) {
			throw new BANK_NOT_FOUND(adminBankId);
		} else {
			return bankname;
		}
	}

}
