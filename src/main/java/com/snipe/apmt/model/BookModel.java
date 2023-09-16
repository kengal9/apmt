package com.snipe.apmt.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModel  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookId;
	private String title;
	private String description;
	private String image;
	//private String video;
	private String synopsis;
	private String price;
	private String editPrice;
	//private String modules;
	private String technology;
}
