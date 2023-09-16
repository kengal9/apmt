package com.snipe.apmt.admin.model;

import java.io.Serializable;


import com.snipe.apmt.admin.domain.CountryDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065059773451498397L;
	private long id;
	private String stateId;
	private String stateName;
	private CountryDomain countryDomain;
	private boolean status;
	private String shortName;
	private long total;
	private long count;
	//private CountryDomain countryDomain;

	
}