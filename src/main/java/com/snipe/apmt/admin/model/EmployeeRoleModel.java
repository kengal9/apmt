package com.snipe.apmt.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.model.RoleModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class EmployeeRoleModel implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 722207735068747483L;
	
	private long id;
	private String employeeRoleId;
	private String employeeRoleName;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	private String path;
	private int total;

}
