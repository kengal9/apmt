package com.snipe.apmt.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.salesmanager.constant.SalesArticleStatusCode;
import com.snipe.apmt.salesmanager.dao.SalesManagerArticleRepository;
import com.snipe.apmt.salesmanager.domain.SalesManagerArticleDomain;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.mapper.UploaderArticleMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.utils.CommonUtils;

@Service
public class SalesManagerArticleServiceImpl implements SalesManagerArticleService {

	@Autowired
	UploaderArticleRepository uploaderArticleRepository;

	@Autowired
	UploaderArticleMapper uploaderArticleMapper;

	@Autowired
	SalesManagerArticleRepository salesManagerArticleRepository;

	@Autowired
	UserService userService;

	@Override
	public List<UploaderArticleModel> getallSalesArticles(int categoryID) throws Exception {
		List<UploaderArticleDomain> uploaderArticleDomine = uploaderArticleRepository
				.getArticlesListByCategoryId(categoryID);
		return uploaderArticleMapper.entityList(uploaderArticleDomine);
	}

	@Override
	public List<UploaderArticleModel> salesNewArticles() throws Exception {
		List<UploaderArticleDomain> uploaderArticleDomine = uploaderArticleRepository
				.findArticlesByStatus(SalesArticleStatusCode.APPROVED.getCode());
		return uploaderArticleMapper.entityList(uploaderArticleDomine);
	}

	@Override
	public String editPrice(long article_id, long editPrice) throws Exception {
		// TODO Auto-generated method stub
		UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository.getByArticleId(article_id);
		long oldPrice = uploaderArticleDomain.getPrice();
		long newPrice = editPrice;
		uploaderArticleDomain.setEditPrice(newPrice);
		uploaderArticleRepository.updatePrice(uploaderArticleDomain.getEditPrice(),
				uploaderArticleDomain.getArticleId());

		// insert into user activity log table
		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "Modified price", uploaderArticleDomain.getTitle());

		SalesManagerArticleDomain salesManagerArticleDomain = new SalesManagerArticleDomain();
		if (uploaderArticleDomain != null) {
			salesManagerArticleDomain.setArticleId(article_id);

			salesManagerArticleDomain.setPrice(oldPrice);
			salesManagerArticleDomain.setEditPrice(newPrice);

			salesManagerArticleRepository.save(salesManagerArticleDomain);

		}
		return "PriceSaved";

	}

}
