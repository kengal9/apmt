package com.snipe.apmt.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "permission")
@JsonInclude(Include.NON_DEFAULT)
public class PermissionDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7972101250581743967L;
	
	@Id
	@Column(name = "permissionId")
	private long permissionId;
	
	@ManyToOne
	@JoinColumn(name = "roleIdFk",referencedColumnName="roleId")
	private RoleDomain roleDomain;
	
	@ManyToOne
	@JoinColumn(name = "menuIdFk",referencedColumnName="menuId")
	private MenuDomain menuDomain;

}
