package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.model.MenuModel;

public interface MenuService {
	
	List<MenuModel> getMenus(int roleId) throws Exception;

	public String saveMenu(MenuModel menuModel) throws Exception;

}
