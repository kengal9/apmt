package com.snipe.apmt.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.snipe.apmt.admin.DAO.StateRepository;
import com.snipe.apmt.admin.domain.StateDomain;
import com.snipe.apmt.admin.mapper.StateMapper;
import com.snipe.apmt.admin.model.StateModel;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	StateMapper stateMapper;

	@Autowired
	StateRepository stateRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(StateServiceImpl.class);

	@Override
	public String addState(StateModel stateModel) throws Exception {
		StateDomain stateDomain = new StateDomain();
		BeanUtils.copyProperties(stateModel, stateDomain);
		try {
		stateRepository.save(stateDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in StateService: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		return "state saved";
	}

	@Override
	public List<StateDomain> getStateList() throws Exception {
		try {
		List<StateDomain> stateDomain = stateRepository.findAll();
		return stateDomain;
		}
		catch (Exception e) {
			logger.error("Exception create in StateServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public StateDomain getStateListById(String stateId) throws Exception {
		try {
		StateDomain stateDomain = stateRepository.findByStateId(stateId);
		return stateDomain;
		}
		catch (Exception e) {
			logger.error("Exception create in StateServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public List<StateDomain> getStatedetails(String countryId) throws Exception {
		try {
		List<StateDomain> stateDomain = stateRepository.findByCountryDomain(countryId);
		return stateDomain;
		}
		catch (Exception e) {
			logger.error("Exception create in StateServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
	}

	@Override
	public String updateState(StateModel stateModel) throws Exception {
		StateDomain stateDomain = new StateDomain();
		BeanUtils.copyProperties(stateModel, stateDomain);
		try {
		stateRepository.save(stateDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		}
		catch(Exception e) {
			logger.error("Exception created in StateService: ", e.getMessage());
			
		}
		return "state updated";
	}

	public int deleteStateById(String stateId) throws Exception {
		try {
		return stateRepository.deleteByStateId(stateId);
		}
		catch (Exception e) {
			logger.error("Exception create in StateServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		
	}

	public StateModel stateCount() throws Exception {
		StateModel stateModel = new StateModel();
		try {
		stateModel.setCount(stateRepository.count());
		}
		catch (Exception e) {
			logger.error("Exception create in StateServiceImpl" + e.getMessage());
			throw new BACKEND_SERVER_ERROR();	
		}
		return stateModel;
	}

	
}
