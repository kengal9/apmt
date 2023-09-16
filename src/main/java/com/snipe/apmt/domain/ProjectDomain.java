package com.snipe.apmt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "project")
@JsonInclude(Include.NON_DEFAULT)
public class ProjectDomain implements Serializable{
	

	private static final long serialVersionUID = 8139802865853705204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "projectId")
	private long projectId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "video")
	private String video;
	
	@Column(name = "Synopsis")
	private String synopsis;
	
	@Column(name = "price")
	private long  price;

	@Column(name = "modules")
	private String modules;
	
	@Column(name = "technologyUsed")
	private String technologyUsed;
	
	@Column(name = "editPrice")
	private long editPrice;
	

	
}
