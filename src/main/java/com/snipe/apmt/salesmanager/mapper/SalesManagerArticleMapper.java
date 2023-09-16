package com.snipe.apmt.salesmanager.mapper;

import com.snipe.apmt.admin.mapper.AbstractModelMapper;
import com.snipe.apmt.salesmanager.domain.SalesManagerArticleDomain;
import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.salesmanager.model.SalesManagerArticleModel;
import com.snipe.apmt.salesmanager.model.SalesManagerModel;

public class SalesManagerArticleMapper extends AbstractModelMapper<SalesManagerArticleModel, SalesManagerArticleDomain> {

	@Override
	public Class<SalesManagerArticleModel> entityType() {
		// TODO Auto-generated method stub
		return SalesManagerArticleModel.class;
	}

	@Override
	public Class<SalesManagerArticleDomain> modelType() {
		// TODO Auto-generated method stub
		return SalesManagerArticleDomain.class;
	}

}
