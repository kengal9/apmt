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
@Table(name = "article_sales")
@JsonInclude(Include.NON_DEFAULT)
public class SalesManagerArticleDomain implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 328860795495301731L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private long id;
	
	@Column(name = "articleId")
	private long articleId;
	
	@Column(name= "NewArticle")
	private String Vreified;
	
	
	@Column(name = "price")
	private long  price;
	
	@Column(name= "editPrice")
	private long editPrice;

	@Column(name = "modules")
	private String modules;
	
	@Column(name = "technologyUsed")
	private String technologyUsed;

}
