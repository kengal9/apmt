package com.snipe.apmt.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.model.CountryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@JsonInclude(Include.NON_DEFAULT)
public class UserDomain implements Serializable {

	private static final long serialVersionUID = 6358356810458349330L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "mobileNumber")
	private long mobileNumber;

	@Column(name = "alternativeNumber")
	private long alternativeNumber;

	@Column(name = "emailId")
	private String emailId;

	@OneToOne
	@JoinColumn(name = "addressId")
	private AddressDomain addressDomain;

	@OneToOne
	@JoinColumn(name = "bankId")
	private BankDomain bankDomain;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private RoleDomain roleDomain;

	@Column(name = "password")
	private String password;

	@Column(name = "dob")
	private String dob;

	@Column(name = "genderId")
	private int genderId;

	@Column(name = "aadharNumber")
	private BigInteger aadharNumber;

	@Column(name = "profileSource")
	private String profileSource;

	@Column(name = "accessToken")
	private String accessToken;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(name = "status", nullable = false)
	private boolean status;

	@Column(name = "employeestatus", nullable = false)
	private boolean employeeStatus;

	@Column(name = "tokenExpires")
	private String tokenExpires;

	
	

}
