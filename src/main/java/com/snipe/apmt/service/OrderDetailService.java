package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.domain.OrderDetailDomain;
import com.snipe.apmt.domain.OrderInput;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.MenuModel;
import com.snipe.apmt.model.OrderModel;

public interface OrderDetailService {

	//public OrderDetailDomain placeOrder(OrderInput orderInput);

	public List<OrderDetailDomain> placeProjectsOrder(OrderModel orderModel, String userId) throws Exception;
	
//	public OrderDetailDomain placeProjectsOrder(OrderInput orderInput,String userId) throws Exception;
	//public OrderDetailDomain placeProjectsOrder(OrderInput orderInput,String userId) throws Exception;
	
	//public OrderDetailDomain placeBooksOrder(OrderInput orderInput, String userId) throws Exception;

	//public OrderDetailDomain placeArticlesOrder(OrderInput orderInput, String userId)throws Exception;

	//public List<OrderDetailDomain> getOrderDetails(String userId);

	//public List<OrderDetailDomain> getAllOrderDetails(String status);

	//public void markOrderAsDelivered(Integer orderId);
	
	public TransactionDetails createTransaction(Double amount);

	

	
}





