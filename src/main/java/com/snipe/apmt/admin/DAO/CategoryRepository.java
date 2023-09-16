package com.snipe.apmt.admin.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.CategoryDomain;


public interface CategoryRepository extends JpaRepository<CategoryDomain, Long> {
	
	public CategoryDomain findByCategoryId(int categoryId);
	
	@Modifying
	@Transactional
	@Query(value="delete from category ca where ca.category_id=?1",nativeQuery=true)
	public int deleteByCategoryId(String CategoryId);
	
	

}
