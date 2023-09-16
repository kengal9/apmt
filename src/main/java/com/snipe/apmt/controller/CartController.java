package com.snipe.apmt.controller;
import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.CartService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class CartController {

	@Autowired
	CartService cartService;
	
			
	@GetMapping(value = "/addToCart/{projectId}/{userId}")
	public ResponseEntity<GenericRes> addToCart(@PathVariable("projectId") long projectId,@PathVariable("userId")String userId) throws Exception {
		return prepareSuccessResponse(cartService.addToCart(projectId, userId));
	}
	
	
	@GetMapping(value = "/getCartDetails/{userId}")
	public ResponseEntity<GenericRes> getCartDetails(@PathVariable("userId")String userId) throws Exception {
		return prepareSuccessResponse(cartService.getCartDetails(userId));
	}
	
	
	@DeleteMapping(value = "/deleteCartItem/{cartId}")
	public ResponseEntity<GenericRes> deleteCartItem(@PathVariable("cartId")Integer cartId) throws Exception {		 
		//return cartService.deleteCartItem(cartId);
		return prepareSuccessResponse(cartService.deleteCartItem(cartId));
	}
    
	@DeleteMapping(value = "/emptyCart/{userId}")
	public void emptyCartByUserId(@PathVariable("userId")String userId) throws Exception {
		cartService.emptyCartByUserId(userId);
		
	}
	@GetMapping(value = "/addBookToCart/{bookId}/{userId}")
	public ResponseEntity<GenericRes> addBookToCart(@PathVariable("bookId") long bookId,@PathVariable("userId")String userId) throws Exception {
		return prepareSuccessResponse(cartService.addBookToCart(bookId, userId));
	}
    
   	@GetMapping(value = "/addArticleToCart/{articleId}/{userId}")
	public ResponseEntity<GenericRes> addArticleToCart(@PathVariable("articleId") long articleId,@PathVariable("userId")String userId) throws Exception {
		return prepareSuccessResponse(cartService.addArticleToCart(articleId, userId));
	}
    
    //for single and multiple projects Checkouts   
	//@GetMapping(value = "/getProjectDetails/{isSingleProductCheckout}/{projectId}/{userId}")	
	//public ResponseEntity<GenericRes> getProjectDetails(@PathVariable("isSingleProductCheckout") boolean isSingleProductCheckout ,
   	@GetMapping(value = "/getProjectDetails/{projectId}/{userId}")
   	public ResponseEntity<GenericRes> getProjectDetails(@PathVariable("projectId")long projectId,@PathVariable("userId")String userId) throws Exception {
		return prepareSuccessResponse(cartService.getProjectDetails(projectId,userId));
	}
   
  
   }
