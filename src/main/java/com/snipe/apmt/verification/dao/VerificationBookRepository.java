package com.snipe.apmt.verification.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.verification.domain.VerificationBookDomain;


public interface VerificationBookRepository extends JpaRepository<VerificationBookDomain, Long> {
	
	public VerificationBookDomain findByBookId(long bookId);

	@Modifying
	@Transactional
	@Query(value = "update verification_book set status_code = ?1, status_desc = ?2 ,book_approved_date = ?3 where book_id = ?4", nativeQuery = true)
	public void updateStatus(int approveStatus, String statusDesc, LocalDateTime date, long bookId);

	@Modifying
	@Transactional
	@Query(value = "update verification_book set status_code = ?1, status_desc = ?2 ,book_rejected_date = ?3 where book_id = ?4", nativeQuery = true)
	public void updateRejectedStatus(int rejectStatus, String statusDesc, LocalDateTime date, long bookId);

}
