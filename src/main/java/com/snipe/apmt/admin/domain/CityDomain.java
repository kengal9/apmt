package com.snipe.apmt.admin.domain;

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

import lombok.Data;

@Data
@Entity
@Table(name = "cities")
public class CityDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4324999829375534666L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	
	@Column(name = "cityId")
	private String cityId;

	@Column(name = "cityName")
	private String cityName;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;
	
	@Column(name = "shortName")
	private String shortName;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "stateIdFk",referencedColumnName="stateId")
	private StateDomain stateDomain;
	
	
	
}
