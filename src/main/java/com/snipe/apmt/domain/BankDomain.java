package com.snipe.apmt.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.snipe.apmt.admin.model.CountryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class BankDomain implements Serializable {

	private static final long serialVersionUID = 8740884905363326592L;

	@Id
	@Column(name = "bankId")
	private String bankId;

	@Column(name = "adminBankId")
	private long adminBankId;

	@Column(name = "userId")
	private String userId;

	@Column(name = "roleId")
	private int roleId;

	@Column(name = "accountNumber")
	private BigInteger accountNumber;

	@Column(name = "ifscCode")
	private String ifscCode;

	@Column(name = "branchName")
	private String branchName;

	@Column(name = "panNumber")
	private String panNumber;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(name = "status")
	private boolean status;

	
	

}
