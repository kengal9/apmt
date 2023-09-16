package com.snipe.apmt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class CartModel {

	private Integer Id;
	//private int orderId;
	private long projectId;
	private long bookId;
	private  long articleId;
	private UserDomain userDomain;
	private String  title;
	private String  description;
	private long  price;
	private long  editPrice;
	private int  status;
	private List<UploaderModel> uploaderModels;
	private List<UploaderArticleModel> uploaderArticleModels;
	private  List<UploaderBookModel> uploaderBookModels;
}
