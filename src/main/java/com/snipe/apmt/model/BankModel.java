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
public class BankModel implements Serializable {

	private static final long serialVersionUID = -4045110730831351375L;

	private String bankId;
	private long adminBankId;
	private String userId;
	private int roleId;
	private BigInteger accountNumber;
	private String ifscCode;
	private String branchName;
	private String panNumber;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;

	
}
