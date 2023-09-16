package com.snipe.apmt.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.snipe.apmt.dao.AddressDAORepository;
import com.snipe.apmt.dao.BookRepository;
import com.snipe.apmt.dao.CartDAORepository;
import com.snipe.apmt.dao.OrderDetailDAORepository;
import com.snipe.apmt.dao.ProjectRepository;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BookDomain;
import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.OrderArticleQuantity;
import com.snipe.apmt.domain.OrderBookQuantity;
import com.snipe.apmt.domain.OrderDetailDomain;
import com.snipe.apmt.domain.OrderInput;
import com.snipe.apmt.domain.OrderProjectQuantity;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.OrderModel;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.utils.CommonUtils;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	
	private static final String SECRET_ID = "o44NE7VPfO2uPY6iY6ePjpYG";
	private static final String KEY= "rzp_test_kxzYNRYSk4w3Ly";
	private static final String CURRENCY = "INR";

	@Autowired
	private OrderDetailDAORepository orderDetailDAORepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UploaderBookRepository uploaderBookRepository;
	
	@Autowired
	private UploaderArticleRepository uploaderArticleRepository;
	
	@Autowired
	private UserDAORepository userDAORepository;
	
	@Autowired
	private CartDAORepository cartDAORepository;
	
	@Autowired
	private AddressDAORepository addressDAORepository;
	
	@Autowired
	UploaderRepository uploaderRepository;
	private static final String ORDER_PLACED = "Placed";
	
	@Override
	public List<OrderDetailDomain> placeProjectsOrder(OrderModel orderModel, String userId) throws Exception{
		List<CartDomain> domain = cartDAORepository.findListByUserId(userId);
		List<OrderDetailDomain> listOfOrderDetails=new ArrayList();
		for (CartDomain cartDomin : domain) {
			OrderDetailDomain orderDetailDomain = new OrderDetailDomain();
			UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(cartDomin.getProjectId());
			if (uploaderDomain != null) {
				orderDetailDomain.setOrderAmount((double) (uploaderDomain.getEditPrice() * 1));
				orderDetailDomain.setProjectId(uploaderDomain.getProjectId());
			}

			UploaderArticleDomain uploaderArticleDomain = uploaderArticleRepository
					.findByarticleId(cartDomin.getArticleId());
			if (uploaderArticleDomain != null) {
				orderDetailDomain.setOrderAmount((double) (uploaderArticleDomain.getEditPrice() * 1));
				orderDetailDomain.setArticleId(uploaderArticleDomain.getArticleId());
			}

			UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(cartDomin.getBookId());
			if (uploaderBookDomain != null) {
				orderDetailDomain.setOrderAmount((double) (uploaderBookDomain.getEditPrice() * 1));
				orderDetailDomain.setBookId(uploaderBookDomain.getBookId());
			}

			UserDomain userDomain = userDAORepository.findByIdOrStatus(userId, true);
//		System.out.println(userDomain);
//			String fullAddress = userDomain.getAddressDomain().getAddress1();
//		orderDetailDomain.setOrderId(idGeneration.generateRandomId());
			orderDetailDomain.setOrderFullName(orderModel.getFullName());
			orderDetailDomain.setOrderFullAddress(orderModel.getFullAddress());
			orderDetailDomain.setOrderAlternateContactNumber(orderModel.getAlternateNumber());
			orderDetailDomain.setOrderContactNumber(orderModel.getContactNumber());
			orderDetailDomain.setOrderStatus(ORDER_PLACED);
			orderDetailDomain.setUserDomain(userId);
			orderDetailDomain.setTransactionId(orderModel.getTransactionId());
			orderDetailDAORepository.save(orderDetailDomain);
			listOfOrderDetails.add(orderDetailDomain);
		}

		return listOfOrderDetails;
	}
	
	
//	public OrderDetailDomain placeProjectsOrder(OrderInput orderInput,String userId) throws Exception {
//		
//		List<OrderProjectQuantity>projectQuantityList =  orderInput.getOrderProjectQuantity();
//		
//		for(OrderProjectQuantity quantity:projectQuantityList) {
//			
//		ProjectDomain projectDomain = 	projectRepository.findByProjectId(quantity.getProjectId());
//		
//		//CartDomain domain = cartDAORepository.findByCartId(userId);
//		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(quantity.getProjectId());
//		UserDomain userDomain = userDAORepository.findById(userId).get();
//		String fullAddress = userDomain.getAddressDomain().getAddress1();
//		
//		OrderDetailDomain orderDetailDomain1 = new OrderDetailDomain();
//		orderDetailDomain1.setOrderFullName(orderInput.getFullName());
//		orderDetailDomain1.setOrderFullAddress(fullAddress);
//		orderDetailDomain1.setOrderAlternateContactNumber(orderInput.getAlternativeNumber());
//		orderDetailDomain1.setOrderContactNumber(orderInput.getContactNumber());
//		orderDetailDomain1.setOrderStatus(ORDER_PLACED);
//		orderDetailDomain1.setOrderAmount((double) (uploaderDomain.getEditPrice()*1));
//		//orderDetailDomain1.setQuantity(1);
//		//orderDetailDomain1.setProjectDomain(projectDomain);
//		orderDetailDomain1.setProjectId(uploaderDomain.getProjectId());
//		orderDetailDomain1.setUserDomain(userId);
//		orderDetailDomain1.setTransactionId(orderInput.getTransactionId());
//		
//		
//		//Empty cart after placing order
//	/*	if(!isSingleProjectCheckout) {
//			List<CartDomain> carts = cartDAORepository.findByUserDomain(userDomain);
//			carts.stream().forEach(x -> cartDAORepository.deleteById(x.getCartId()));
//		}	*/	
//		return orderDetailDAORepository.save(orderDetailDomain1);
//	}		
//		return null;
//
//	}



	@Override	
	public TransactionDetails createTransaction(Double amount) {
		try {
			
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", (amount * 100));
		jsonObject.put("currency",CURRENCY);
		
		
		RazorpayClient razorpayClient = new RazorpayClient(KEY,SECRET_ID);
		
		Order order =  razorpayClient.orders.create(jsonObject);
		
		TransactionDetails transactionDetails = prepareTransactionDetails(order);
		
		return transactionDetails;
		//System.out.println(order);
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private TransactionDetails prepareTransactionDetails(Order order) {
		
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionDetails transactionDetails = new TransactionDetails(orderId,currency,amount,KEY);
		transactionDetails.setAmount(amount);
		transactionDetails.setCurrency(currency);
		transactionDetails.setOrderId(orderId);
		transactionDetails.setKey(KEY);
		return transactionDetails;
	}


	
	
	//@Override
	/*public OrderDetailDomain placeBooksOrder(OrderInput orderInput, String userId) throws Exception {
		
        List<OrderBookQuantity>bookQuantityList =  orderInput.getOrderBookQuantity();		
		for(OrderBookQuantity quantity:bookQuantityList) {
		
		UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(quantity.getBookId());
		UserDomain userDomain = userDAORepository.findById(userId).get();
		OrderDetailDomain orderDetailDomain1 = new OrderDetailDomain();
		orderDetailDomain1.setOrderFullName(orderInput.getFullName());
		orderDetailDomain1.setOrderFullAddress(orderInput.getFullAddress());
		orderDetailDomain1.setOrderAlternateContactNumber(orderInput.getAlternativeNumber());
		orderDetailDomain1.setOrderContactNumber(orderInput.getContactNumber());
		orderDetailDomain1.setOrderStatus(ORDER_PLACED);
		orderDetailDomain1.setOrderAmount((double) (uploaderBookDomain.getEditPrice()*quantity.getQuantity()));
		orderDetailDomain1.setUploaderBookDomain(uploaderBookDomain);
		orderDetailDomain1.setUserDomain(userId);
		return orderDetailDAORepository.save(orderDetailDomain1);
	}		
		return null;

	}*/

	//@Override
	/*public OrderDetailDomain placeArticlesOrder(OrderInput orderInput, String userId) throws Exception {
		
		List<OrderArticleQuantity>articleQuantityList =  orderInput.getOrderArticleQuantity();	
		for(OrderArticleQuantity quantity:articleQuantityList) {
		UploaderArticleDomain articleDomain = uploaderArticleRepository.findByArticleId(quantity.getArticleId());
		UserDomain userDomain = userDAORepository.findById(userId).get();
		OrderDetailDomain orderDetailDomain1 = new OrderDetailDomain();
		orderDetailDomain1.setOrderFullName(orderInput.getFullName());
		orderDetailDomain1.setOrderFullAddress(orderInput.getFullAddress());
		orderDetailDomain1.setOrderAlternateContactNumber(orderInput.getAlternativeNumber());
		orderDetailDomain1.setOrderContactNumber(orderInput.getContactNumber());
		orderDetailDomain1.setOrderStatus(ORDER_PLACED);
		orderDetailDomain1.setOrderAmount((double) (articleDomain.getEditPrice()*quantity.getQuantity()));
		orderDetailDomain1.setUploaderArticleDomain(articleDomain);
		orderDetailDomain1.setUserDomain(userId);
		return orderDetailDAORepository.save(orderDetailDomain1);
	}		
		return null;

	}*/
	
	//showing all orders to admin irrespective of user
	//@Override
	/*public List<OrderDetailDomain> getAllOrderDetails(String status) {
		List<OrderDetailDomain> orderDetails = new ArrayList<>();
		if(status.equals("All")) {
		orderDetailDAORepository.findAll().forEach(x ->orderDetails.add(x));
		}
		else {
			orderDetailDAORepository.findByOrderStatus(status).forEach(
					x -> orderDetails.add(x));
			}			
		return orderDetails;
	}
*/
	

	

	//mark order as delivered (admin)
	//@Override
	/*public void markOrderAsDelivered(Integer orderId) {
		OrderDetailDomain OrderDetailDomain = orderDetailDAORepository.findById(orderId).get();
		if(OrderDetailDomain != null)
			OrderDetailDomain.setOrderStatus("delivered");
		    orderDetailDAORepository.save(OrderDetailDomain);		
	}*/
    //my order details acc to user
	//@Override
	/*public List<OrderDetailDomain> getOrderDetails(String userId) {
		//UserDomain userDomain = userDAORepository.findById(userId).get();
		List<OrderDetailDomain> userList = orderDetailDAORepository.findByUserDomain(userId);	
		return userList;
	}*/
	
	
	
	
	}

	




