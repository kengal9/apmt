package com.snipe.apmt.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_log")
public class UserLogDomain implements Serializable {

	private static final long serialVersionUID = -5880660832445645759L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "userId")
	private String userId;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "mobileNumber")
	private long mobileNumber;
	@Column(name = "alternativeNumber")
	private long alternativeNumber;
	@Column(name = "emailId")
	private String emailId;
	@Column(name = "dob")
	private String dob;
	@Column(name = "genderId")
	private int genderId;
	@Size(min = 12, max = 12)
	private BigInteger aadharNumber;
	@Column(name="roleId")
	private int roleId;
	@Column(name = "roleName")
	private String roleName;
	@Column(name="projectTitle")
	private String projectTitle;
	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;
	@Column(name = "status", nullable = false)
	private boolean status;
	@Column(name = "userActivity")
	private String userActivity;
}