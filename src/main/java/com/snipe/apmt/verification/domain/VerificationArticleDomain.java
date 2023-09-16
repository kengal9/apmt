package com.snipe.apmt.verification.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_article")
@JsonInclude(Include.NON_DEFAULT)
public class VerificationArticleDomain implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6226905781997598534L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "articleId", unique = true)
	private long articleId;

	@Column(name = "verifierId")
	private String verifierId;

	@Column(name = "statusCode")
	private int statusCode;

	@Column(name = "statusDesc")
	private String statusDesc;

	@Column(name = "articleVerifiedDate")
	private LocalDateTime articleVerifiedDate;

	@Column(name = "articleApprovedDate")
	private LocalDateTime articleApprovedDate;

	@Column(name = "articleRejectedDate")
	private LocalDateTime articleRejectedDate;

}
