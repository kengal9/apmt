package com.snipe.apmt.admin.service;

import java.util.List;
import java.util.Map;

import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;

public interface AdminDashboardService {

	List<UploaderModel> listNewProject();

	List<UploaderModel> listOldProject();

	Map<String, Long> countOfRecords();
	
	List<UploaderBookModel> listNewBook();
	
	List<UploaderBookModel> listOldBook();
	
	Map<String,Long>countOfBookRecords();

    List<UploaderArticleModel> listNewArticle();
	
	List<UploaderArticleModel> listOldArticle();
	
	Map<String,Long>countOfArticleRecords();
}
