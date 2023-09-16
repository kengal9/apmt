package com.snipe.apmt.service;


import java.util.List;

import com.snipe.apmt.domain.BookDomain;
import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.model.CartModel;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.model.UploaderModel;


public interface CartService {

	
public	CartDomain addToCart(long projectId, String userId) throws Exception;

public List<CartDomain>getCartDetails(String userId)throws Exception;

public String deleteCartItem(Integer cartId);

public CartDomain addBookToCart(long bookId, String userId);

public CartDomain addArticleToCart(long articleId, String userId);

//public CartModel getProjectDetails(boolean isSingleProjectCheckout, long projectId,String userId);

public CartModel getProjectDetails(long projectId,String userId);

//public List<UploaderBookDomain> getBookDetails(boolean isSingleBookCheckout, long bookId, String userId);

//public List<UploaderArticleDomain> getArticleDetails(boolean isSingleArticleCheckout, long articleId, String userId);

public void emptyCartByUserId(String userId);




}

