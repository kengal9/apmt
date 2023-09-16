package com.snipe.apmt.mapper;

import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.domain.MenuDomain;
import com.snipe.apmt.model.GenderModel;
import com.snipe.apmt.model.MenuModel;

@Component
public class MenuMapper extends AbstractModelMapper<MenuModel, MenuDomain>{

	@Override
	public Class<MenuModel> entityType() {
		// TODO Auto-generated method stub
		return MenuModel.class;
	}

	@Override
	public Class<MenuDomain> modelType() {
		// TODO Auto-generated method stub
		return MenuDomain.class;
	}

}
