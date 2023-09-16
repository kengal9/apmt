package com.snipe.apmt.mapper;

import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.model.AddressModel;

public class AddressMapper extends AbstractModelMapper<AddressModel, AddressDomain> {

	@Override
	public Class<AddressModel> entityType() {
		return AddressModel.class;
	}

	@Override
	public Class<AddressDomain> modelType() {
		return AddressDomain.class;
	}

}
