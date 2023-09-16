package com.snipe.apmt.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class UserRoleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5203132530393887428L;
	private String uuId;
	private RoleDomain roleDomain;
	private UserDomain userDomain;
	private boolean status;
	private boolean isActive;
	private String userId;
	private int total;
	private String webtokenId;
	private String apptokenId;

	
}
