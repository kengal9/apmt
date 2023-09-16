package com.snipe.apmt.salesmanager.domain;

import java.io.Serializable;

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
@Table(name="sales_book")
@JsonInclude(Include.NON_DEFAULT)

public class SalesManagerBookDomain implements Serializable{
	
	private static final long serialVersionUID = 4471080936070601429L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	@Column(name = "id")
	private long id;
	
	@Column(name = "bookId")
	private long bookId;
	
	@Column(name= "NewBook")
	private String Verified;
	
	@Column(name = "price")
	private long  price;
	
	@Column(name= "editPrice")
	private long editPrice;
	
	@Column(name = "technology")
	private String technology;
}
