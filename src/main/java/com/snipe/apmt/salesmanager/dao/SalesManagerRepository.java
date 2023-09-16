package com.snipe.apmt.salesmanager.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.verification.domain.VerificationDomain;

public interface SalesManagerRepository extends JpaRepository<SalesManagerDomain, Long> {
	
	public SalesManagerDomain findByProjectId(long projectId);
	
	/*@Modifying
	@Transactional
	@Query(value = "update saless set editprice = ?1 where project_id = ?2 AND status = 2",  nativeQuery = true)
	public void updatePrice(int editprice, long projectId  );*/
	
	
	
	
	
	
	
}
