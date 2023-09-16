package com.snipe.apmt.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.salesmanager.dao.SalesManagerBookRepository;
import com.snipe.apmt.salesmanager.domain.SalesManagerBookDomain;
import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderBookMapper;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.verification.constant.BookStatusCode;


@Service
public class SalesManagerBookServiceImpl implements SalesManagerBookService{

	@Autowired
	UploaderBookRepository uploaderBookRepository;
	
	@Autowired
	UploaderBookMapper uploaderBookMapper;
	
	@Autowired
	SalesManagerBookRepository salesBookRepository;
	
	@Autowired
	UserService userService;
	
	
	@Override
	public List<UploaderBookModel> getallSalesBooks(int categoryID) throws Exception {
	 List<UploaderBookDomain> bookDomain = uploaderBookRepository.getBooksListByCategoryId(categoryID);
		return uploaderBookMapper.entityList(bookDomain);
	}

	@Override
	public List<UploaderBookModel> salesNewBooks() throws Exception {		
		List<UploaderBookDomain> bookDomain = uploaderBookRepository.findBookByStatus(BookStatusCode.APPROVED.getCode());
		return uploaderBookMapper.entityList(bookDomain);
	}

	@Override
	public String editPrice(long book_id, long editPrice) throws Exception {
		
		UploaderBookDomain uploaderbookDomain = uploaderBookRepository.findBookByBookId(book_id);
		long oldPrice = uploaderbookDomain.getPrice();
		long newPrice = editPrice;
		uploaderbookDomain.setEditPrice(newPrice);
		uploaderBookRepository.updatePrice(uploaderbookDomain.getEditPrice(),uploaderbookDomain.getBookId());
		
		//insert into user activity log table
		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "Modified price", uploaderbookDomain.getTitle());
		
		SalesManagerBookDomain salesManagerDomain = new SalesManagerBookDomain();
		if (uploaderbookDomain != null) {
			salesManagerDomain.setBookId(uploaderbookDomain.getBookId());
			
			salesManagerDomain.setPrice(oldPrice);
			salesManagerDomain.setEditPrice(newPrice);
		
			salesBookRepository.save(salesManagerDomain);
		
		}
		return "PriceSaved";
	
	} 


}
