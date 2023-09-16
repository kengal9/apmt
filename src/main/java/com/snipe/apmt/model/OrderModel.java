package com.snipe.apmt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2058007038732745979L;
	
	private Integer orderId;
	private String fullName;
	private String fullAddress;
	private long contactNumber;
	private long alternateNumber;
	private String orderStatus;
	private Double orderAmount;
	private int orderProjectQuantity;
	private long projectId;
	private String userDomain;
	private UploaderBookDomain uploaderBookDomain;
	private UploaderArticleDomain uploaderArticleDomain;
	private String transactionId;

}
