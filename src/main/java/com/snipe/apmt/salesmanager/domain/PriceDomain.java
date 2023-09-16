package com.snipe.apmt.salesmanager.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "price")
@JsonInclude (Include.NON_DEFAULT)

public class PriceDomain implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	//@ManyToOne
	//@JoinColumn (name = "projectIdFK",referencedColumnName= "projectId" )
	//private UploaderDomain uploaderDomain;
	
	@Column(name= "projectId")
	private long projectId;

	
	@Column(name= "price")
	private long price;
	
	@Column(name = "Discount" ,nullable = false)
	private int Discount;
	
	@Column(name="editPrice",nullable = false )
	private long editPrice;	
	
	
	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;

	@Column(name = "modificationDate", nullable = false)
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	/*
	 * public long setprojectIdFK(long projectId) { return projectId; // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */
	//public void setprojectIdFK(long projectId) {
		// TODO Auto-generated method stub
		
	//}
	
	
	
	
	


}
