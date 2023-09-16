package com.snipe.apmt.uploader.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploaderBookModel implements Serializable {

	
	private static final long serialVersionUID = 7563052626519802063L;
	
	private long id;
	private long bookId;
	private String title;
	private String description;
	private String image;
	private String author;
	private String synopsis;
	private long price;		
	private String technology;
	private int categoryId;
	private int status;
	private String bookCode;
	private String uploaderId;
	private LocalDateTime dateCreated;
	//private LocalDateTime lastUpdated;
	private long editPrice;
	private String emailId;
	private String image2;
	private byte[] image1 ;

}


