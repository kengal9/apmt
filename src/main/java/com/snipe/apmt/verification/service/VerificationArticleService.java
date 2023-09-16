package com.snipe.apmt.verification.service;

import java.util.List;

import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.verification.model.VerificationArticleModel;

public interface VerificationArticleService {
	
	public List<UploaderArticleModel> getVerifiedArticles() throws Exception;

	public List<UploaderArticleModel> getNewArticles() throws Exception;

	public List<UploaderArticleModel> getArticlesListByCategoryId(int categoryID) throws Exception;

//	public String verifyArticle(long articleId, boolean verifiedStatus) throws Exception;
	public String verifyArticle(VerificationArticleModel verificationArticleModel) throws Exception;

	public String approveArticle(long articleId, boolean approveStatus) throws Exception;

	public String rejectArticle(long articleId, boolean rejectStatus) throws Exception;

	public List<VerificationArticleModel> getVerificationArticleDetails() throws Exception;
	
	public VerificationArticleModel getVerificationArticleDetailsByID(long articleId)throws Exception;

	

	

}
