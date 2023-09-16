package com.snipe.apmt.salesmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snipe.apmt.salesmanager.domain.SalesManagerBookDomain;

public interface SalesManagerBookRepository extends JpaRepository<SalesManagerBookDomain, Long>  {

	
	public SalesManagerBookDomain findByBookId(long bookId);
}
