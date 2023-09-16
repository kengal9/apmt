package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.UserLogDomain;

public interface UserLogRepository extends JpaRepository<UserLogDomain, String>{
	@Query(value = "select * from user_log ul where ul.role_id in (select r.role_id from role r where r.role_name =?1)", nativeQuery = true)
	public List<UserLogDomain> getActivityDetails(String roleName);

}
