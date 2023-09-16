package com.snipe.apmt.admin.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.StateDomain;

public interface StateRepository extends JpaRepository<StateDomain, Long> {

	public StateDomain findByStateId(String stateId);

	@Query(value="select * from states s where s.country_id_fk=?1",nativeQuery=true )
	public List<StateDomain> findByCountryDomain(String countryIdFk);

	@Modifying
	@Transactional
	@Query(value="delete from states s where s.state_id=?1",nativeQuery=true)
	public int deleteByStateId(String stateId);

	//@Query(value="update states set ")
	//public StateDomain update(StateDomain stateDomain);

}
