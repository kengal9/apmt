package com.snipe.apmt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.snipe.apmt.admin.domain.StateDomain;
import com.snipe.apmt.domain.OrderDetailDomain;
import com.snipe.apmt.domain.UserDomain;

public interface OrderDetailDAORepository  extends JpaRepository<OrderDetailDomain,Integer> {
	
	public OrderDetailDomain save(OrderDetailDomain orderDetailDomain);
	
	public List<OrderDetailDomain>findByOrderStatus(String status);

	public List<OrderDetailDomain> findByUserDomain(String userId);
	
		}
