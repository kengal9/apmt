package com.snipe.apmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="verified_user")
public class VerifiedUserDomain {
	
	@Id
	@Column(name="id")
	private String userId;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="verifiedStatusCode")
	private int verifiedStatusCode;
	
	@Column(name="verifiedStatusDesc")
	private String verifiedStatusDesc;
	

}
