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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_bank")
public class AdminBankDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4324999829375534666L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	
	@Column(name = "adminBankId")
	private String adminBankId;

	@Column(name = "adminBankName")
	private String adminBankName;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;
	
	private Integer total;
	
	

	
}
