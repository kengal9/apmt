package com.snipe.apmt.salesmanager.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")

public class SalesManagerBookModel implements Serializable
{

	private long id;
	private long bookId;
	private String NewBook;
	private long price;
	private long editPrice;
	//private String modules;
	private String technology;
}
