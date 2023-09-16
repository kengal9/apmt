package com.snipe.apmt.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.model.CountryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userrole")
@JsonInclude(Include.NON_DEFAULT)
public class UserRoleDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092494671697450609L;

	@Id
	private String uuId;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private RoleDomain roleDomain;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserDomain userDomain;

	@Column(name = "createdDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modifiedDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(name = "webtokenId")
	private String webtokenId;

	@Column(name = "apptokenId")
	private String apptokenId;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean isActive;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;

}
