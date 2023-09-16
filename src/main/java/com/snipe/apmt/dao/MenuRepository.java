package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.domain.MenuDomain;
import com.snipe.apmt.domain.RoleDomain;

public interface MenuRepository extends JpaRepository<MenuDomain,String> {

	@Query(value="select * from apmt.menu where role_id_fk=?1",nativeQuery=true)
	public List<MenuDomain> getMenusByRoleId(int roleId);
	

}
