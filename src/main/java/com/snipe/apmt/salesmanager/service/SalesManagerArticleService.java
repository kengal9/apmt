package com.snipe.apmt.salesmanager.service;

import java.util.List;

import com.snipe.apmt.uploader.model.UploaderArticleModel;

public interface SalesManagerArticleService {

	List<UploaderArticleModel> getallSalesArticles(int categoryID) throws Exception;

	List<UploaderArticleModel> salesNewArticles() throws Exception;

	String editPrice(long article_id, long editPrice) throws Exception;

}
