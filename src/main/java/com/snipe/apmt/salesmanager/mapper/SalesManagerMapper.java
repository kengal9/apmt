package com.snipe.apmt.salesmanager.mapper;

import com.snipe.apmt.mapper.AbstractModelMapper;
import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.salesmanager.model.SalesManagerModel;
import org.springframework.stereotype.Component;

@Component
public class SalesManagerMapper extends AbstractModelMapper<SalesManagerModel, SalesManagerDomain> {

	@Override
	public Class<SalesManagerModel> entityType() {
		// TODO Auto-generated method stub
		return SalesManagerModel.class ;
	}

	@Override
	public Class<SalesManagerDomain> modelType() {
		// TODO Auto-generated method stub
		return SalesManagerDomain.class;
	}
	

}
