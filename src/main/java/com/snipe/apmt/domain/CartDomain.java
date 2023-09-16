package com.snipe.apmt.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cart")
public class CartDomain {

	
	
	public CartDomain(long projectId, String userId) {
		
	}

	public CartDomain()
	{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "id")
	private Integer cartId;
	
	//@Column(name = "orderId")
//	private int orderId;
	
	//one cart id is associated with one product id
	@Column(name = "projectId")
	private long projectId;
	
	@Column(name = "bookId")
	private long bookId;
	
	
	@JoinColumn(name = "articleId")
	private  long articleId;
	
	//one cart id is asociated with one userid
	@OneToOne
	@JoinColumn(name = "userId")
	private UserDomain userDomain;	
	
  
	@Column(name = "title")
	private String  title;
	
	@Column(name = "description")
	private String  description;
	
	@Column(name = "price")
	private long  price;
	
	@Column(name = "editPrice")
	private long  editPrice;
	
	@Column(name = "status")
	private int  status;
	
		}
          