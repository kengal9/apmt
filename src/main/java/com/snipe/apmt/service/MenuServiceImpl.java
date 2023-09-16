package com.snipe.apmt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.dao.MenuRepository;
import com.snipe.apmt.domain.MenuDomain;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.mapper.MenuMapper;
import com.snipe.apmt.model.MenuModel;
import com.snipe.apmt.model.ProjectModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.utils.IDGeneration;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
//@Override
@Transactional
public class MenuServiceImpl implements MenuService {
	

	@Autowired
	MenuMapper menuMapper;
	

//	@Autowired
//	MenuModel menuModel;
	

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	IDGeneration idGeneration;
	
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	//static ObjectMapper mapper = new ObjectMapper();

	public List<MenuModel> getMenus(int roleId) throws Exception {
		try {
		List<MenuDomain> menuDomain = menuRepository.getMenusByRoleId(roleId);
		//List<MenuModel> menuModel1 = new ArrayList<MenuModel>();
//		if (menuDomain == null)
//			return null;
		//BeanUtils.copyProperties(menuDomain, menuModel1);
		 //menuModel1 = mapper.readValue(CommonUtils.getBlobData(menuDomain.getMenus()), MenuModel.class);
		return menuMapper.entityList(menuDomain);
		}
		catch (Exception e) {
			logger.error("Exception created in MenuServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
}
	@Override
	public String saveMenu(MenuModel menuModel) throws Exception {
		MenuDomain menuDomain = new MenuDomain();
		menuModel.setMenuId(idGeneration.generateRandomId());
		BeanUtils.copyProperties(menuModel, menuDomain);
		try {
		 menuRepository.save(menuDomain);
		}
		catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in MenuService: ", e.getMessage());

		}
		return "Menu Saved";
	}

	


	
}