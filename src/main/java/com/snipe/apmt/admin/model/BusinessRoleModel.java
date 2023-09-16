package com.snipe.apmt.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class BusinessRoleModel implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5363449656129787327L;

	private long id;
	private String businessRoleId;
	private String businessRoleName;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	private String path;
	private int total;
}
