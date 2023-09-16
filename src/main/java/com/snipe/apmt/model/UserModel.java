package com.snipe.apmt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Size;

import com.snipe.apmt.admin.model.CountryModel;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BankDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

	private static final long serialVersionUID = -5880660832445645759L;

	private String id;
	private String firstName;
	private String lastName;
	private int roleId;
	private int employeeRoleId;
	private String addressId;
	private String bankId;
	@Size(min = 10, max = 10)
	private long mobileNumber;
	@Size(min = 10, max = 10)
	private long alternativeNumber;
	private String emailId;
	private String password;
	private String confirmPassword;
	private String dob;
	private int genderId;
	@Size(min = 12, max = 12)
	private BigInteger aadharNumber;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	private String tokenExpires;
	private String roleName;
	private String path;
	private String country;
	private String state;
	private String city;
	private String bank;
	private String countryId;
	private String stateId;
	private String cityId;
	private BigInteger pinCode;
	private BigInteger accountNumber;
	private String ifscCode;
	private String branchName;
	private String panNumber;
	private BigInteger doorNumber;
	private String street;
	private String address1;
	private String profileSource;
	private long adminBankId;
	private long count;
	private boolean employeestatus;
	private String warningMessage;
	private List<BankModel> bankModel;
	private AddressModel addressModel;
	private BankDomain bankDomain;
	private AddressDomain addressDomain;
	
}