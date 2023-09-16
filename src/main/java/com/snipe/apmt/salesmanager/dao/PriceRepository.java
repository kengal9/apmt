package com.snipe.apmt.salesmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.salesmanager.domain.PriceDomain;
//import com.snipe.apmt.salesmanager.domain.PriceDomain;

 

public interface PriceRepository extends JpaRepository <PriceDomain,Long>{

	public PriceDomain findByProjectId(long projectId);

	//public void save(long editPrice);
	
	
	
	/*@Modifying
	@Transactional
	@Query(value="select * from price pr where pr.projectIdFK=?1",nativeQuery=true )
	public List<CityDomain> findByStateDomain(String stateId);*/
	
	
	/*@Modifying
	@Transactional
	@Query(value = "update project set editPrice = ?1 where projectIdFK=?2", nativeQuery = true)
	public void  updatePrice(long price, long projectIdFK);*/
	
}
