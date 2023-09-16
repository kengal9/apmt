package com.snipe.apmt.verification.dao;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.verification.domain.VerificationArticleDomain;

public interface VerificationArticleRepository extends JpaRepository<VerificationArticleDomain, Long>{
	
	public VerificationArticleDomain findByArticleId(long Id);

	@Modifying
	@Transactional
	@Query(value = "update verification_article set status_code = ?1, status_desc = ?2 ,article_approved_date = ?3 where article_id = ?4", nativeQuery = true)
	public void updateStatus(int approveStatus, String statusDesc, LocalDateTime date, long articleId);

	@Modifying
	@Transactional
	@Query(value = "update verification_article set status_code = ?1, status_desc = ?2 ,article_rejected_date = ?3 where article_id = ?4", nativeQuery = true)
	public void updateRejectedStatus(int rejectStatus, String statusDesc, LocalDateTime date, long articleId);

}
