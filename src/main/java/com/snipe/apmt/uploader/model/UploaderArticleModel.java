package com.snipe.apmt.uploader.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploaderArticleModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8117372288405459763L;

	private long id;
	private long articleId;
	private String title;
	private String description;
	private String authors;
	private String publishers;
	private String image;
//	private String video;
	private String synopsis;
	private long price;
	private String modules;
	private String technologyUsed;
	private int categoryId;
	private int status;
	private String articleCode;
	private String uploaderId;
	private LocalDateTime dateCreated;
	private LocalDateTime lastUpdated;
	private long editPrice;
	private byte[] image1;
	private String image2;
	private String emailId;
//	private byte[] video1;

}
