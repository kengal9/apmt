package com.snipe.apmt.model;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.domain.MenuDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class PermissionModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6619454787801493932L;

	
	private long permissionId;

	private RoleDomain roleDomain;
	private MenuDomain menuDomain;
}
