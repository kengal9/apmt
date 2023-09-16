package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.snipe.apmt.domain.UserRoleDomain;
import com.snipe.apmt.model.UserRoleModel;

public interface UserRoleDaoRepository extends JpaRepository<UserRoleDomain, String>{

	static List<UserRoleDomain> getUserRoleByUserDomain(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	static UserRoleModel userRoleCount(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	String deleteUserRoleByUuId(String uuId);

}
