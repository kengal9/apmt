package com.snipe.apmt.verification.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.verification.domain.VerificationDomain;

public interface VerificationRepository extends JpaRepository<VerificationDomain, Long> {

	public VerificationDomain findByProjectId(long projectId);

	@Modifying
	@Transactional
	@Query(value = "update verification set status_code = ?1, status_desc = ?2 ,project_approved_date = ?3 where project_id = ?4", nativeQuery = true)
	public void updateStatus(int approveStatus, String statusDesc, LocalDateTime date, long projectId);

	@Modifying
	@Transactional
	@Query(value = "update verification set status_code = ?1, status_desc = ?2 ,project_rejected_date = ?3 where project_id = ?4", nativeQuery = true)
	public void updateRejectedStatus(int rejectStatus, String statusDesc, LocalDateTime date, long projectId);

}
