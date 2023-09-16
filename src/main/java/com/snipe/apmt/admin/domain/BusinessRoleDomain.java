package com.snipe.apmt.admin.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "businessRole")
@JsonInclude(Include.NON_DEFAULT)
public class BusinessRoleDomain implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8365246311603495997L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "businessRoleId")
	private String businessRoleId;

	@Column(name = "businessRoleName")
	private String businessRoleName;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;

	@Column(name = "path")
	private String path;

}
