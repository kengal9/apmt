package com.snipe.apmt.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.domain.MenuDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class RoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667630235606843632L;

	private int roleId;
	private String roleName;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	private String path;
	private int total;
	private long count;
	//private MenuModel menuModel;
	//private MenuDomain menuDomain;

	
}
