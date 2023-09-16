package com.snipe.apmt.admin.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.AdminBankDomain;
import com.snipe.apmt.admin.model.AdminBankModel;

public interface AdminBankRepository extends JpaRepository<AdminBankDomain, Long>
{
	public AdminBankDomain findByAdminBankId(String adminBankId);

	//public AdminBankDomain findByAdminBankName(String adminBankName);
	
	@Modifying
	@Transactional
	@Query(value="delete from admin_bank a where a.admin_bank_id=?1",nativeQuery=true)
	public int deleteByAdminBankId(String adminBankId);
}
