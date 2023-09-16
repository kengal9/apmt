package com.snipe.apmt.uploader.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.uploader.domain.UploaderDomain;

public interface UploaderRepository extends JpaRepository<UploaderDomain, Long> {
	
	public List<UploaderDomain> findByProjectId(long projectId);
	
	public List<UploaderDomain> findByUploaderId(String uploaderId);
	
	public UploaderDomain findProjectByProjectId(long projectId);
	
	/*@Query(value = "select * from project p where p.project_id=?1", nativeQuery = true)
	public List<UploaderDomain> findByProjectId_arrayOutput(long projectId);*/

	public List<UploaderDomain> findProjectsByStatus(int code);
	
	@Query(value = "select count(*) from project p where p.project_id=?1", nativeQuery = true)
	public long existProjectId(long projectId);

	@Query(value = "select * from project p where p.category_id=?1", nativeQuery = true)
	public List<UploaderDomain> getProjectsListByCategoryId(int categoryID);

	@Modifying
	@Transactional
	@Query(value = "update project set status = ?1 where project_id = ?2", nativeQuery = true)
	public void updateProjectStatus(int vstatus, long projectId);
	
	@Modifying
	@Transactional
	@Query(value = "update project set edit_price = ?1 where project_id=?2", nativeQuery = true)
	public void  updatePrice(long price, long projectId);

	@Modifying
	@Transactional
	@Query(value = "delete from project p where p.project_id=?1", nativeQuery = true)
	public void deleteByProjectId(long projectId);
    
	@Query(value = "select ifnull(max(project_id),100)+1 as projectId from project ", nativeQuery = true)
	public int createProjectId();
	
	@Query(value = "select * from project p where p.status= 2", nativeQuery = true)
	public List<UploaderDomain> getApprovedProjects();

}
