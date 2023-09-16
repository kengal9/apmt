package com.snipe.apmt.uploader.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;


public interface UploaderBookRepository extends JpaRepository<UploaderBookDomain,Long>{
	
    public List<UploaderBookDomain> findByBookId(long bookId);
	
	public UploaderBookDomain findBookByBookId(long bookId);	
	
	public List<UploaderBookDomain> findBookByStatus(int code);
	
	@Query(value = "select count(*) from book b where b.book_id=?1", nativeQuery = true)
	public long existBookId(long bookId);

	@Query(value = "select * from book b where b.category_id=?1", nativeQuery = true)
	public List<UploaderBookDomain> getBooksListByCategoryId(int categoryID);

	@Modifying
	@Transactional
	@Query(value = "update book set status = ?1 where book_id = ?2", nativeQuery = true)
	public void updateBookStatus(int vstatus, long bookId);
	
	@Modifying
	@Transactional
	@Query(value = "update book set edit_price = ?1 where book_id=?2", nativeQuery = true)
	public void  updatePrice(long price, long bookId);

	@Modifying
	@Transactional
	@Query(value = "delete from book b where b.book_id=?1", nativeQuery = true)
	public void deleteByBookId(long bookId);

	
	@Query(value = "select ifnull(max(book_id),100)+1 as bookId from book ", nativeQuery = true)
	public int createBookId();
	
	@Query(value = "select * from book b where b.status= 2", nativeQuery = true)
	public List<UploaderBookDomain> getApprovedBooks();

}
