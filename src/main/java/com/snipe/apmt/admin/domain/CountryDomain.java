package com.snipe.apmt.admin.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class CountryDomain implements Serializable {
	
	private static final long serialVersionUID = 3129621435637234018L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "countryId")
	private String countryId;

	@Column(name = "shortName")
	private String shortname;

	@Column(name = "countryName")
	private String countryName;

	@Column(name = "countryCode")
	private String CountryCode;

	@Column(name = "creationDate")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;


	

}
