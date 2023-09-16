package com.snipe.apmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderArticleQuantity {
	
    private Integer articleId;	
	private  Integer quantity;
}
