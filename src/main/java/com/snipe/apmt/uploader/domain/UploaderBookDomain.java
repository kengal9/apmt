package com.snipe.apmt.uploader.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "book")
@JsonInclude(Include.NON_DEFAULT)
public class UploaderBookDomain implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4384905031057270278L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "id")
	private long id;
	
	@Column( unique = true, nullable = false,length = 50 )
	private long bookId;

	@Column(unique = true,length = 100 , nullable = false,name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;
	
	@Column(name="author")
	private String author;

	@Column(name = "synopsis")
	private String synopsis;

	@Column(name = "price",length = 10 , nullable = false)
	private long price;
		
	@Column(name = "technology", nullable = false,length = 100 )
	private String technology;

	@Column(name = "categoryId",nullable = false,length = 10)
	private int categoryId;

	@Column(name = "status")
	private int status;

	@Column(name="bookCode")
	private String bookCode;
	
	@Column(name = "uploader_id")
	private String uploaderId;

	@Column(name = "dateCreated")
	private LocalDateTime dateCreated;
	
	//@Column(name = "lastUpdated")
	//private LocalDateTime lastUpdated;
	
	@Column(name = "editPrice",length =10,nullable = false)
	private long editPrice;

	@Column(name="emailId")
	private String emailId;
}

	
