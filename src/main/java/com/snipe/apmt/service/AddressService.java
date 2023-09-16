package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.model.AddressModel;
import com.snipe.apmt.model.UserModel;

public interface AddressService {

	public AddressDomain saveAddressInfo(UserModel userModel) throws Exception;

	public String deleteAddressById(String addressId) throws Exception;

	public List<AddressModel> getAddressByStateId(String stateId, int roleId, boolean status) throws Exception;

	public List<AddressModel> getAddressByCityId(String cityId, int roleId, boolean status) throws Exception;

	public List<AddressModel> getAddressByUserId(String userId, int roleId) throws Exception;

}
