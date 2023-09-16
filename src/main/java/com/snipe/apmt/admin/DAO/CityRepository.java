package com.snipe.apmt.admin.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.CityDomain;

public interface CityRepository extends JpaRepository<CityDomain, Long> {

	public CityDomain findByCityId(String cityId);

	@Modifying
	@Transactional
	@Query(value="delete from cities ci where ci.city_id=?1",nativeQuery=true)
	public int deleteByCityId(String cityId);
	
	@Query(value="select * from cities ci where ci.state_id_fk=?1",nativeQuery=true )
	public List<CityDomain> findByStateDomain(String stateId);

}
