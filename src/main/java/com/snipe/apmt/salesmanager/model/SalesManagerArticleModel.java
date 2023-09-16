package com.snipe.apmt.salesmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesManagerArticleModel {
	
	private long id;
	private long articleId;
	private String NewArticle;
	private long price;
	private long editPrice;
	private String modules;
	private String technologyUsed;

}
