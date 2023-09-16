package com.snipe.apmt.admin.DAO;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.UserDomain;

public interface AdminOperationsDAORepository extends JpaRepository<UserDomain, String> {

	@Query(value = "select * from user u where u.role_id in(select r.role_id from role r where r.role_name in ('student','purchaser'))", nativeQuery = true)
	public List<UserDomain> getListOfExternalUsers();

	@Query(value = "select * from user u where u.role_id in(select r.role_id from role r where r.role_name in ('verificationManager','salesManager'))", nativeQuery = true)
	public List<UserDomain> getListOfInternalUsers();

	@Query(value = "select * from user u where u.first_name = ?1 and u.aadhar_number =?2 and u.email_id =?3", nativeQuery = true)
	public UserDomain getIdForUser(String firstName, BigInteger aadharNumber, String emailId);
}
