package com.snipe.apmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProjectQuantity {

	private Integer projectId;	
	private  Integer quantity;
}
