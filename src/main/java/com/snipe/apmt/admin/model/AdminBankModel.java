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
public class AdminBankModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667630235606843632L;
	private long id;
	
	private String adminBankId;
	private String adminBankName;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private Integer status;
	private long count;
	

	

}