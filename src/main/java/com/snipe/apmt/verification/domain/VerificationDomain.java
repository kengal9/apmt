package com.snipe.apmt.verification.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.domain.ProjectDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification")
@JsonInclude(Include.NON_DEFAULT)

public class VerificationDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "projectId", unique = true)
	private long projectId;

	@Column(name = "verifierId")
	private String verifierId;

	@Column(name = "statusCode")
	private int statusCode;

	@Column(name = "statusDesc")
	private String statusDesc;

	@Column(name = "projectVerifiedDate")
	private LocalDateTime projectVerifiedDate;

	@Column(name = "projectApprovedDate")
	private LocalDateTime projectApprovedDate;

	@Column(name = "projectRejectedDate")
	private LocalDateTime projectRejectedDate;
}
