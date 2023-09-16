package com.snipe.apmt.admin.service;

import java.util.List;


import com.snipe.apmt.admin.model.CountryModel;

public interface CountryService {

	public String addCountry(CountryModel countryModel) throws Exception;

	public List<CountryModel> getCountryList() throws Exception;

	public CountryModel getCountryListById(String countryId) throws Exception;

	public String updateCountry(CountryModel countryModel) throws Exception;

	public int deleteCountryById(String countryId) throws Exception;

}
