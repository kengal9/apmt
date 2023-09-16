package com.snipe.apmt.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;

public interface UserDAORepository extends JpaRepository<UserDomain, String> {

	public UserDomain findByEmailId(String emailId);

	@Query(value="select * from apmt.user where id=?1 AND status=?2",nativeQuery=true) 
	public UserDomain findByIdOrStatus(String Id, boolean status);

	//@Query(value="select * from user u where u.role_id=?1 AND u.status=?2 AND u.employeestatus=?3",nativeQuery=true )
	@Query(value="select * from apmt.user  where role_id=?1 AND status=?2 AND employeestatus=?3",nativeQuery=true)
	public List<UserDomain> findByRoleIdAndStatusAndEmployeeStatus(int roleId, boolean status, boolean employeeStatus);

	//public UserDomain findById(String userId);

	@Query(value="select COUNT(*) from apmt.user where role_id=?1 AND status=?2",nativeQuery=true)
	public long getUserCount(int roleId, boolean status);
	
	@Query(value="From UserDomain where aadharNumber=?1")
	public UserDomain validateAadhar(BigInteger aadharNumber);

	@Query(value="select * from user u where u.mobile_number=?1 AND u.email_id=?2 AND u.role_id=?3",nativeQuery=true )
	public UserDomain findByMobileNumberOrEmailIdAndRoleDomain(long mobileNumber, String emailId, int roleId);

	//@Query(value="select * from user u where u.mobile_number=? AND u.email_id=?",nativeQuery=true)
	
	
	//public String updateBank(UserModel userModel);

	//public String updateAddress(UserModel userModel);
	@Modifying
	@Query(value="update user set bank_id=?1 where id=?2",nativeQuery=true )
	public void updateUserBankId(String bankId,String id );
	@Modifying
	@Query(value="update user set address_id=?1 where id=?2",nativeQuery=true )
	public void updateUserAddressId(String addressId, String id);

	public List<UserDomain> findByMobileNumberOrEmailId(long mobileNumber, String emailId);

	//public UserDomain getUserByMobileNumberOrEmailId(long mobileNumber, String emailId, int roleId);

	//public void save(UserModel userModel);
	
	@Query(value="select email_id from user u where u.role_id in (2,3) ",nativeQuery=true)	
	public String[] getMailIdByRoleId();
	
	@Query(value="select email_id from user u where u.role_id in (2) ",nativeQuery=true)	
	public String getEMailIdByRoleId();
	
	
}
