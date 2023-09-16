package com.snipe.apmt.uploader.domain;

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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.domain.ProjectDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
@JsonInclude(Include.NON_DEFAULT)
public class UploaderDomain implements Serializable {

	private static final long serialVersionUID = 6676951634400549905L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private long id;
	
	@Column( unique = true, nullable = false,length = 50 )
	private long projectId;

	@Column(unique = true,length = 100 , nullable = false,name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;

	@Column(name = "video")
	private String video;

	@Column(name = "synopsis")
	private String synopsis;

	@Column(name = "price",length = 10 , nullable = false)
	private long price;

	@Column(name = "modules")
	private String modules;

	@Column(name = "technologyUsed", nullable = false,length = 100 )
	private String technologyUsed;

	@Column(name = "categoryId",nullable = false,length = 10)
	private int categoryId;

	@Column(name = "status")
	private int status;

	@Column(name = "projectcode")
	private String projectCode;
	
	@Column(name = "uploader_id")
	private String uploaderId;

	@Column(name = "dateCreated")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "lastUpdated")
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	
	@Column(name = "editPrice")
	private long editPrice;
	
	@Column(name="emailId")
	private String emailId;
	//@Column(name = "imagePath")
	//private String imagePath;
	
	

}
