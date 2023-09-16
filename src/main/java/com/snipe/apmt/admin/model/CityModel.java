package com.snipe.apmt.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.snipe.apmt.admin.domain.StateDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7801768889220828341L;
	/**
	 * 
	 */
	private long id;
	private String cityId;
	private String cityName;
	private StateDomain stateDomain;
	private String shortName;
	private boolean status;

	

	
}
