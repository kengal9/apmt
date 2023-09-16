package com.snipe.apmt.uploader.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
@JsonInclude(Include.NON_DEFAULT)
public class UploaderArticleDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5833663351077902207L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "article_id", unique = true, nullable = false,length = 50 )
	private long articleId;

	@Column(name = "article_Title", unique = true,length = 100 , nullable = false)
	private String title;

	@Column(name = "description")
	private String description;
	
	@Column(name = "authors")
	private String authors;
	
	@Column(name = "publishers")
	private String publishers;

	@Column(name = "image_name")
	private String imageName;

//	@Column(name = "video_name")
//	private String videoName; 

	@Column(name = "price_article", length = 10 , nullable = false)
	private long price;

	@Column(name = "articles_technoloy", nullable = false,length = 100 )
	private String technologyUsed;

	@Column(name = "category", nullable = false,length = 10)
	private int categoryId;

	@Column(name = "status")
	private int status;

	@Column(name = "article_code")
	private String articleCode;

	@Column(name = "user_id")
	private String uploaderId;

	@Column(name = "created_date")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp	
	private LocalDateTime lastUpdated;

	@Column(name = "edit_price")
	private long editPrice;

	@Column(name="emailId")
	private String emailId;
}
