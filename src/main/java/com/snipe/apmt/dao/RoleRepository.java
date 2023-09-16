package com.snipe.apmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.RoleDomain;

public interface RoleRepository extends JpaRepository<RoleDomain,Integer > {

	public RoleDomain findByRoleId(int roleId);

	@Modifying
	@Transactional
	@Query(value="delete from role r where r.role_id=?1",nativeQuery=true )
	public int deleteByRoleId(int roleId);
	
//	RoleDomain findById(int roleId);

}
