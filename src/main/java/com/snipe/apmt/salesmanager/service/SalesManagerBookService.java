package com.snipe.apmt.salesmanager.service;

import java.util.List;

import com.snipe.apmt.uploader.model.UploaderBookModel;


public interface SalesManagerBookService {

public List<UploaderBookModel> getallSalesBooks( int categoryID) throws Exception;
	
	public List<UploaderBookModel> salesNewBooks() throws Exception;
	
	public String editPrice (long book_id,long editPrice) throws Exception;
	
		
	
}