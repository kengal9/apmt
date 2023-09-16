package com.snipe.apmt.verification.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationArticleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2838785497564713741L;
	
	
	private long id;
	private long articleId;
	private String verifierId;
	private int statusCode;
	private String statusDesc;
	private LocalDateTime articleVerifiedDate;
	private LocalDateTime articleApprovedDate;
	private LocalDateTime articleRejectedDate;


}
