package com.snipe.apmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.domain.UserDomain;

public interface ProjectRepository extends JpaRepository<ProjectDomain,Long> {
		public ProjectDomain findByProjectId(long projectId);

		@Modifying
		@Transactional
		@Query(value="delete from project p where p.project_id=?1",nativeQuery=true )
		public long deleteByProjectId(long projectId);

		
		
}


