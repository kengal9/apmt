package com.snipe.apmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.GenderDomain;
import com.snipe.apmt.domain.ProjectDomain;

public interface GenderRepository extends JpaRepository<GenderDomain,String> {

	GenderDomain findByGenderId(String genderId);

	@Modifying
	@Transactional
	@Query(value="delete from gender g where g.gender_id=?1",nativeQuery=true )
	public int deleteByGenderId(String genderId);

}
