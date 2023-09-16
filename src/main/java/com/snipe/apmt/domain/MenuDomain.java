package com.snipe.apmt.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.domain.StateDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
@JsonInclude(Include.NON_DEFAULT)
public class MenuDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5930270450589767979L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menuId")
	private int menuId;
//	
//	@ManyToMany
//    @JoinColumn(name = "roleIdFk" ,referencedColumnName="roleId")
//    Set<RoleDomain> roleId;
	
	@ManyToOne
	@JoinColumn(name = "roleIdFk",referencedColumnName="roleId")
	private RoleDomain roleDomain;
	
	private String name;
	//private Blob menus;
	
	private boolean status;
	

}
