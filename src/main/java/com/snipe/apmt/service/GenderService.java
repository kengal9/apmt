package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.model.GenderModel;

public interface GenderService {

	public String saveGender(GenderModel genderModel) throws Exception;

	//Object getGenderById(long genderId);

	public String updateGender(GenderModel genderModel)throws Exception;

	public String deleteByGenderId(String genderId) throws Exception;

	public GenderModel getGenderById(String genderId);

	public List<GenderModel> getGenderList() throws Exception;

}
