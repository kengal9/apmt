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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "states")
public class StateDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2837436206071338244L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "stateId")
	private String stateId;

	@Column(name = "stateName")
	private String stateName;

	@ManyToOne
	@JoinColumn(name = "countryIdFk" ,referencedColumnName="countryId")
	private CountryDomain countryDomain;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean status;
	

	@Column(name = "shortName")
	private String shortName;

	
}
