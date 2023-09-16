package com.snipe.apmt.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "role")
@JsonInclude(Include.NON_DEFAULT)
public class RoleDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5962499219032209533L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;

	@Column(name = "roleName")
	private String roleName;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;
	

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;

	@Column(name = "path")
	private String path;
	
//	@OneToMany
//	Set<MenuDomain> menuIdFk;
	
//	@OneToMany
//	@JoinTable(
//			name="permission",
//			joinColumns=@JoinColumn(name="role_id"),
//			inverseJoinColumns=@JoinColumn(name="menu_id"))
//	Set<MenuDomain> menuId;
	
			


}
