package com.snipe.apmt.salesmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snipe.apmt.salesmanager.domain.SalesManagerArticleDomain;
import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;

public interface SalesManagerArticleRepository extends JpaRepository<SalesManagerArticleDomain, Long> {
	
	public SalesManagerDomain findByArticleId(long articleId);

}
