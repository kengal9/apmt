package com.snipe.apmt.salesmanager.mapper;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.salesmanager.domain.PriceDomain;
//import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.salesmanager.model.PriceModel;
//import com.snipe.apmt.salesmanager.model.SalesManagerModel;

public class PriceMapper extends AbstractModelMapper<PriceModel, PriceDomain> {

	@Override
	public Class<PriceModel> entityType() {
		// TODO Auto-generated method stub
		return PriceModel.class;
	}

	@Override
	public Class<PriceDomain> modelType() {
		// TODO Auto-generated method stub
		return PriceDomain.class;
	}

}
