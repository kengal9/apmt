package com.snipe.apmt.uploader.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploaderModel implements Serializable {

	private static final long serialVersionUID = 1496045743022575864L;

	private long id;
	private long projectId;
	private String title;
	private String description;
	private String image;
	private String video;
	private String synopsis;
	private long price;
	private String modules;
	private String technologyUsed;
	private int categoryId;
	private int status;
	private String projectCode;
	private String uploaderId;
	private LocalDateTime dateCreated;
	private LocalDateTime lastUpdated;
	private long editPrice;
	private byte[] image1 ;
	private String image2;
	private String video2;
	private byte[] video1 ;
	private String emailId;
	
	

}