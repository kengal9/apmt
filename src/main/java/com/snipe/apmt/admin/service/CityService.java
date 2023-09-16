package com.snipe.apmt.admin.service;

import java.util.List;


import com.snipe.apmt.admin.domain.CityDomain;
import com.snipe.apmt.admin.model.CityModel;

public interface CityService {

	public String addCity(CityModel cityModel) throws Exception;

	public List<CityDomain> getCityList() throws Exception;

	public CityDomain getCityListById(String cityId) throws Exception;

	public String updateCity(CityModel cityModel) throws Exception;

	public int deleteCityById(String cityId) throws Exception;

	public List<CityDomain> getCityList(String stateId) throws Exception;

}
