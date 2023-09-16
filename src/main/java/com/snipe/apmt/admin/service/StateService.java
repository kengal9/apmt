package com.snipe.apmt.admin.service;

import java.util.List;


import com.snipe.apmt.admin.domain.StateDomain;
import com.snipe.apmt.admin.model.StateModel;

public interface StateService {

	public String addState(StateModel stateModel) throws Exception;

	public List<StateDomain> getStateList() throws Exception;

	public StateDomain getStateListById(String stateId) throws Exception;

	public List<StateDomain> getStatedetails(String countryId) throws Exception;

	public String updateState(StateModel stateModel) throws Exception;

	public int deleteStateById(String stateId) throws Exception;

	public StateModel stateCount() throws Exception;

	//public String deleteById(String stateId) throws Exception; 
		// TODO Auto-generated method stub
		
	

}
