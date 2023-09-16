package com.snipe.apmt.admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.CountryDomain;
import com.snipe.apmt.admin.domain.EmployeeRoleDomain;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleDomain, Long> {
	
	@Modifying
	@Transactional
	@Query(value="delete from employee_role e where e.employee_role_id=?1",nativeQuery=true)
	public int deleteEmployeeRoleById(String employeeRoleId);
	
	public EmployeeRoleDomain findByEmployeeRoleId(String employeeRoleId);

}
