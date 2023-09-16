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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressDomain implements Serializable {

	private static final long serialVersionUID = -4131787376814032939L;

	@Id
	@Column(name = "addressId")
	private String addressId;

	@Column(name = "userId")
	private String userId;

	@Column(name = "roleId")
	private int roleId;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "countryId")
	private String countryId;

	@Column(name = "stateId")
	private String stateId;

	@Column(name = "cityId")
	private String cityId;
	
	@Column(name = "pinCode")
	private BigInteger pinCode;

	@Column(name = "doorNumber")
	private BigInteger doorNumber;

	@Column(name = "street")
	private String street;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(name = "status")
	private boolean status;

}