package com.snipe.apmt.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInput {

	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private String alternativeNumber;
	private String transactionId;
	private List<OrderProjectQuantity>OrderProjectQuantity;
	//private Integer OrderProjectQuantity;
	private List<OrderBookQuantity>OrderBookQuantity;
	private List<OrderArticleQuantity>OrderArticleQuantity;
	
	
	
}
