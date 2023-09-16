package com.snipe.apmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifiedUserModel {

	private String id;
	
	private String emailId;
	
	private int verifiedStatusCode;
	
	private String verifiedStatusDesc;
}
