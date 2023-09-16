package com.snipe.apmt.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.BankDomain;

public interface BankDAORepository extends JpaRepository<BankDomain, String> {

	@Modifying
	@Transactional
	@Query(value="delete from bank b where b.bank_id=?1",nativeQuery=true )
	public String deleteByBankId(String bankId);

	public BankDomain findByUserId(String userId);
	
	//public BankDomain existsByAccountNumber( BigInteger accountNumber);

	public BankDomain existsByPanNumber(String panNumber);

	public BankDomain findByAccountNumber(BigInteger accountNumber);
	
	public BankDomain findByPanNumber(String panNumber);
//	AdminBankDomain findById(String bankId) throws Exception;

	public BankDomain findByBankId(String bankId);
}
