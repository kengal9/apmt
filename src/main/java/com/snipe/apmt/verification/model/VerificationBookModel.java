package com.snipe.apmt.verification.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationBookModel implements Serializable {
	
	
	private static final long serialVersionUID = -1646817497121640837L;
	
	private long id;
	private long bookId;
	private String verifierId;
	private int statusCode;
	private String statusDesc;
	private LocalDateTime bookVerifiedDate;
	private LocalDateTime bookApprovedDate;
	private LocalDateTime bookRejectedDate;

}
