package com.snipe.apmt.uploader.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderModel;

public interface UploaderArticleService {

	String saveArticle(UploaderArticleModel uploderArticleModel,MultipartFile image) throws Exception;

	UploaderArticleModel getArticleByArticleId(long articleId) throws Exception;

	String updateArticle(UploaderArticleModel uploderArticleModel) throws Exception;

	String deleteByArticleId(long articleId);

	List<UploaderArticleModel> getListByArticleId();

	byte[] downloadFile(String fileName) throws FileNotFoundException;

	String UploadFileInStorageService(MultipartFile file, String fileName) throws Exception;

	String deleteFile(String fileName);

	List<UploaderArticleModel> getApprovedArticles();

	
}
