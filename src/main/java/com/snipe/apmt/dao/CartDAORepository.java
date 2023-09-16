package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.UserDomain;


public interface CartDAORepository extends JpaRepository<CartDomain,Integer>  {

	public List<CartDomain> findByUserDomain(UserDomain userDomain);
	
	@Modifying
	@Transactional
	@Query(value = "delete from cart c where c.cart_id=?", nativeQuery = true)
	public void deleteByCartId(Integer cartId);

	//public String deleteUserById(String userId);
	@Modifying
	@Transactional
	@Query(value = "delete from cart c where  status =0 and c.user_id=?", nativeQuery = true)
	public void emptyCartByUserId(String userId);
	
	@Query(value = "select * from cart c where c.user_id= ?", nativeQuery = true)
	public CartDomain findByCartId(String userId);
	
	@Query(value = "select * from cart c where c.user_id= ?", nativeQuery = true)
	public List<CartDomain> findListByUserId(String userId);
	
}
