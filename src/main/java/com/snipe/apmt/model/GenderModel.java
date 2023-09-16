package com.snipe.apmt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderModel implements Serializable {

	private static final long serialVersionUID = -1675878986235574896L;
	
	private int id;
	private String genderId;
	private String genderName;


}
