package com.snipe.apmt.admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.BusinessRoleDomain;


public interface BusinessRoleRepository extends JpaRepository<BusinessRoleDomain, Long>{

	@Modifying
	@Transactional
	@Query(value="delete from business_role b where b.business_role_id=?1",nativeQuery=true)
	public int deleteBusinessRoleById(String businessRoleId);
	
	public BusinessRoleDomain findByBusinessRoleId(String businessRoleId);
}
