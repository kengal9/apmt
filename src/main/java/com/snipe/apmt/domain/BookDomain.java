package com.snipe.apmt.domain;

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
public class BookDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "bookId")
	private long bookId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	//@Column(name = "video")
	//private String video;
	
	@Column(name = "Synopsis")
	private String synopsis;
	
	@Column(name = "price")
	private long  price;

	//@Column(name = "modules")
	//private String modules;
	
	@Column(name = "technology")
	private String technology;
	
	@Column(name = "editPrice",length =10,nullable = false)
	private long editPrice;


}
