package com.snipe.apmt.model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.snipe.apmt.domain.RoleDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430128450067452214L;

	private String menuId;
	//private RoleModel roleModel;
	//private RoleDomain roleDomain;
	private String name;
	//private Blob menus;
	//private List<MenuTreeModel> menus = new ArrayList<MenuTreeModel>();
	private RoleDomain roleDomain;
	private boolean status;
 
}
