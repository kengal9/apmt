package com.snipe.apmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.VerifiedUserDomain;

public interface VerifiedUserRepository extends JpaRepository<VerifiedUserDomain,Integer>{

	public VerifiedUserDomain save(VerifiedUserDomain verifiedUserDomain);

	@Query(value = "select * from verified_user v where v.verified_status_code = 1", nativeQuery = true)
	public List<VerifiedUserDomain> getVerifiedUsersList();

	@Query(value = "select verified_status_desc from verified_user v where v.id =?1", nativeQuery = true)
	public String checkUserVerified(String userId);
}
