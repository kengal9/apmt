package com.snipe.apmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.domain.OrderDetailDomain;
import com.snipe.apmt.domain.OrderInput;
import com.snipe.apmt.model.OrderModel;
import com.snipe.apmt.service.OrderDetailService;
import com.snipe.apmt.service.TransactionDetails;


@RestController
@RequestMapping("/v1")
public class OderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@PostMapping({"/placeProjectsOrder/{userId}"})
	public List<OrderDetailDomain> placeProjectsOrder(@PathVariable("userId") String userId, @RequestBody OrderModel oerderModel) throws Exception {
		return orderDetailService.placeProjectsOrder(oerderModel,userId);
		
	}
	
	/*@PostMapping({"/placeBooksOrder/{userId}"})
	public OrderDetailDomain placeBooksOrder(@RequestBody OrderInput orderInput,@PathVariable("userId") String userId) throws Exception {
		return orderDetailService.placeBooksOrder(orderInput,userId);
		
	}
	
	@PostMapping({"/placeArticlesOrder/{userId}"})
	public OrderDetailDomain placeArticlesOrder(@RequestBody OrderInput orderInput,@PathVariable("userId") String userId) throws Exception {
		return orderDetailService.placeArticlesOrder(orderInput,userId);
		
	}*/
	
	// showing my orders to users	
	/* @PostMapping({"/getOrderDetails/{userId}"})
	 public List<OrderDetailDomain> getOrderDetails(@PathVariable("userId") String userId)throws Exception {
     return orderDetailService.getOrderDetails(userId);		
	}
	*/
	//showing all orders to admin irrespective of user    
	//@GetMapping({"/getAllOrderDetails/{status}"})
	/*public List<OrderDetailDomain> getAllOrderDetails(@PathVariable("status") String status)throws Exception {
	return orderDetailService.getAllOrderDetails(status);		
	}*/
	
	//mark order as delivered (admin)
	/*@GetMapping({"/markOrderAsDelivered/{orderId}"})
	public void markOrderAsDelivered(@PathVariable("orderId")Integer orderId)throws Exception {
	orderDetailService.markOrderAsDelivered(orderId);		
	}
	*/
	@GetMapping({"/createTransaction/{amount}"})
	
	public TransactionDetails createTransaction(@PathVariable(name="amount")Double amount) {
		return orderDetailService.createTransaction(amount);
	}
}
