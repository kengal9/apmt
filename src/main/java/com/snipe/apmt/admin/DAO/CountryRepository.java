package com.snipe.apmt.admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.CountryDomain;

public interface CountryRepository extends JpaRepository<CountryDomain, Long>{

	public CountryDomain findByCountryId(String countryId);

	@Modifying
	@Transactional
	@Query(value="delete from country c where c.country_id=?1",nativeQuery=true)
	public int deleteByCountryId(String countryId);

}
