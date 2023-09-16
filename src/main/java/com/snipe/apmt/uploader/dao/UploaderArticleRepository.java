package com.snipe.apmt.uploader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.model.UploaderArticleModel;

public interface UploaderArticleRepository extends JpaRepository<UploaderArticleDomain, Long> {
	
	/*@Modifying
	@Transactional
	@Query(value="delete from article  p where p.article_id=?1",nativeQuery=true )
	public long deleteByArticleId(long articleId);*/

	public UploaderArticleDomain findByArticleId(long articleId);
	
	@Query(value = "select count(*) from article p where p.article_id=?1", nativeQuery = true)
	public long existArticleId(long articleId);
	
	public UploaderArticleDomain getByArticleId(long articleId);
	
	public void deleteByArticleId(long articleId);
	
	public List<UploaderArticleDomain> getArticleByArticleId(long articleId);
	
	public List<UploaderArticleDomain> findArticlesByStatus(int code);
	
	@Query(value = "select * from article p where p.category=?1", nativeQuery = true)
	public List<UploaderArticleDomain> getArticlesListByCategoryId(int categoryID);
	
	@Modifying
	@Transactional
	@Query(value = "update article set status = ?1 where article_id = ?2", nativeQuery = true)
	public void updateArticleStatus(int vstatus, long articleId);
	
	@Modifying
	@Transactional
	@Query(value = "update article set edit_price = ?1 where article_id=?2", nativeQuery = true)
	public void  updatePrice(long price, long articleId);

	@Query(value = "select ifnull(max(article_id),100)+1 as articleId from article ", nativeQuery = true)
	public int createArticleId();

	public UploaderArticleDomain findByarticleId(long articleId);
	
	@Query(value = "select * from article a where a.status= 2", nativeQuery = true)
	public List<UploaderArticleDomain> getApprovedArticles();
	
}
