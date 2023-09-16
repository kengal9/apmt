package com.snipe.apmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.snipe.apmt.domain.BookDomain;
import com.snipe.apmt.domain.ProjectDomain;


public interface BookRepository extends JpaRepository<BookDomain,Long>{
	
	public BookDomain findByBookId(long bookId);

	@Modifying
	@Transactional
	@Query(value="delete from book b where b.book_id=?1",nativeQuery=true )
	public long deleteByBookId(long bookId);

}
