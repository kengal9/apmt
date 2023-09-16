package com.snipe.apmt.salesmanager.domain;

import java.io.Serializable;
//import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
@JsonInclude(Include.NON_DEFAULT)

public class SalesManagerDomain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "projectId")
	private long projectId;
	
	@Column(name= "NewProject")
	private String Vreified;
		
	@Column(name = "price")
	private long  price;
	
	@Column(name= "editPrice")
	private long editPrice;

	@Column(name = "modules")
	private String modules;
	
	@Column(name = "technologyUsed")
	private String technologyUsed;

	
	
	
	
	
	/*@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "video")
	private String video;
	
	@Column(name = "synopsis")
	private String synopsis;*/
	

}
