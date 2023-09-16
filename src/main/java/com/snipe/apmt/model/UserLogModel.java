package com.snipe.apmt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogModel implements Serializable {

	private static final long serialVersionUID = -5880660832445645759L;

	private int id;
	private String firstName;
	private String lastName;
	@Size(min = 10, max = 10)
	private long mobileNumber;
	@Size(min = 10, max = 10)
	private long alternativeNumber;
	private String emailId;
	private String dob;
	private int genderId;
	@Size(min = 12, max = 12)
	private BigInteger aadharNumber;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private boolean status;
	private String roleName;
	private String country;
	private String state;
	private String city;
    private String projectTitle;
	private String userActivity;

}