package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.AddressDomain;

public interface AddressDAORepository extends JpaRepository<AddressDomain, String> {

	
	public AddressDomain findByAddressId(String addressId) throws Exception;

	@Modifying
	@Transactional
	@Query(value="delete from address a where a.address_id=?1",nativeQuery=true )
	
	public String deleteByAddressId(String addressId) throws Exception;

	//@Query(value="select * from address a where a.state_id=? AND a.role_id=?",nativeQuery=true )
	public List<AddressDomain> findByStateIdAndRoleId(String stateId, int roleId);

	//@Query(value="select * from address a where a.city_id=? AND a.role_id=?",nativeQuery=true )
	public List<AddressDomain> findByCityIdOrRoleId(String cityId, int roleId);

	
	//@Query(value="select * from address a where a.role_id=? AND a.user_id=?",nativeQuery=true )
	public List<AddressDomain> findByUserIdOrRoleId(String userId, int roleId);

}
