package com.snipe.apmt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.snipe.apmt.admin.model.CountryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel implements Serializable {

	private static final long serialVersionUID = 5288829442375786772L;

	private String addressId;
	private String userId;
	private int roleId;
	private String address1;
	private String address2;
	private String countryId;
	private String stateId;
	private String cityId;
	private BigInteger pinCode;
	private BigInteger doorNumber;
	private String street;
	private String country;
	private String state;
	private String city;	
	private long mobileNumber;
	private String firstName;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	
}