package com.snipe.apmt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetailDomain implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer orderId;	
	
	@Column(name = "firstName")	
	private String orderFullName;	
	
	@Column(name = "fullAddress")	
	private String orderFullAddress;
		
	@Column(name = "mobileNumber")	
	private long orderContactNumber;
	
	@Column(name = "alternativeNumber")	
	private long orderAlternateContactNumber;
	
	@Column(name = "orderStatus")	
	private String orderStatus;
	
	@Column(name = "orderAmount")
	private Double orderAmount;
	
	
	@Column(name = "projectId")
	private long projectId;
	
	@Column(name = "articlesId")
	private long articleId;
	
	@Column(name = "booksId")
	private long bookId;
	
	
	//@OneToOne
	@Column(name = "userId")
	private String userDomain;
	
	/*@OneToOne
	@JoinColumn(name = "bookId")
	private UploaderBookDomain uploaderBookDomain;
	
	@OneToOne
	@JoinColumn(name = "articleId")
	private UploaderArticleDomain uploaderArticleDomain;*/
	
	@Column(name="transactionId")
	private String transactionId;
	
		}
	

