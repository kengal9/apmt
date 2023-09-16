package com.snipe.apmt.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065059773451498397L;

	private long id;
	private String countryId;
	private String shortname;
	private String countryName;
	private String CountryCode;
	// private LocalDateTime creationDate;
	// private LocalDateTime modificationDate;
	private boolean status;

	

	
}
