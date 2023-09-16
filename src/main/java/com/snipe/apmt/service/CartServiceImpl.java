package com.snipe.apmt.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.OneToOne;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.snipe.apmt.dao.BookRepository;
import com.snipe.apmt.dao.CartDAORepository;
import com.snipe.apmt.dao.ProjectRepository;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.BookDomain;
import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.ecommerce.constant.CartStatusCode;
import com.snipe.apmt.model.CartModel;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;


@Service

public class CartServiceImpl implements CartService{

	
	@Autowired 
	CartDAORepository cartDAORepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserDAORepository userDAORepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UploaderRepository uploaderRepository;
	
	@Autowired
	UploaderBookRepository uploaderBookRepository;
	
	@Autowired
	UploaderArticleRepository uploaderArticleRepository;
	
	@Autowired
	UploaderMapper uploaderMapper;
	@Override
	public CartDomain addToCart(long projectId,String userId) throws Exception {
		
		//ProjectDomain projectDomain = projectRepository.findByProjectId(projectId);	
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		UserDomain userDomain = userDAORepository.findById(userId).get();
		
		//filtering duplicates
		List<CartDomain> cartList = cartDAORepository.findByUserDomain(userDomain);
		List<CartDomain>filteredList = cartList.stream().filter(x->x.getProjectId() == projectId).collect(Collectors.toList());
		if(filteredList.size()>0) {
			return null;
		}
		
		//if(projectId != 0  && userId !=null)
		//{
			CartDomain cartDomain = new CartDomain(projectId,userId);	
			
			//cartDomain.setUploaderDomain(uploaderDomain);
			cartDomain.setUserDomain(userDomain);
			cartDomain.setTitle(uploaderDomain.getTitle());
			cartDomain.setDescription(uploaderDomain.getDescription());
			cartDomain.setPrice(uploaderDomain.getPrice());
			cartDomain.setEditPrice(uploaderDomain.getEditPrice());
			cartDomain.setProjectId(uploaderDomain.getProjectId());
			//cartDomain.setOrderId(0);
			cartDomain.setStatus(CartStatusCode.CART.getCode());
			//cartDomain.setQuantity(1);
			//cartDomain.setUploaderDomain(uploaderDomain);
			return cartDAORepository.save(cartDomain);
		//}
		
		//return null;
	}
	
	@Override
	public CartDomain addBookToCart(long bookId, String userId) {
		//BookDomain bookDomain = bookRepository.findByBookId(bookId);	
		UploaderBookDomain uploaderBookDomain = (UploaderBookDomain) uploaderBookRepository.findBookByBookId(bookId);
		UserDomain userDomain = userDAORepository.findById(userId).get();
		
		//filtering duplicates
		List<CartDomain> cartList = cartDAORepository.findByUserDomain(userDomain);
		List<CartDomain>filteredList = cartList.stream().filter(x->x.getBookId() == bookId).collect(Collectors.toList());
		if(filteredList.size()>0) {
			return null;
		}
		
		if(bookId !=0 && userId !=null)
		{
			CartDomain cartDomain = new CartDomain(bookId,userId);	
			//cartDomain.setBookDomain(bookId);
			//cartDomain.setUploaderBookDomain(uploaderBookDomain);
			cartDomain.setUserDomain(userDomain);
			cartDomain.setTitle(uploaderBookDomain.getTitle());
			cartDomain.setDescription(uploaderBookDomain.getDescription());
			cartDomain.setPrice(uploaderBookDomain.getPrice());
			cartDomain.setEditPrice(uploaderBookDomain.getEditPrice());
			cartDomain.setBookId(uploaderBookDomain.getBookId());
			//cartDomain.setUploaderBookDomain(uploaderBookDomain);
			cartDAORepository.save(cartDomain);
		}
		
		return null;
	}


	@Override
	public CartDomain addArticleToCart(long articleId, String userId) {
		
		UploaderArticleDomain uploaderArticleDomain = (UploaderArticleDomain) uploaderArticleRepository.findByArticleId(articleId);		
		UserDomain userDomain = userDAORepository.findById(userId).get();
		
		//filtering duplicvates
		List<CartDomain> cartList = cartDAORepository.findByUserDomain(userDomain);
		List<CartDomain>filteredList = cartList.stream().filter(x->x.getArticleId() == articleId).collect(Collectors.toList());
		if(filteredList.size()>0) {
			return null;
		}
		
		if(articleId !=0 && userId !=null)
		{
			CartDomain cartDomain = new CartDomain(articleId,userId);	
			//cartDomain.setBookDomain(bookId);
			//cartDomain.setUploaderArticleDomain(uploaderArticleDomain);
			cartDomain.setUserDomain(userDomain);
			cartDomain.setTitle(uploaderArticleDomain.getTitle());
			cartDomain.setDescription(uploaderArticleDomain.getDescription());
			cartDomain.setPrice(uploaderArticleDomain.getPrice());
			cartDomain.setEditPrice(uploaderArticleDomain.getEditPrice());
			cartDomain.setArticleId(uploaderArticleDomain.getArticleId());
			//cartDomain.setUploaderArticleDomain(uploaderArticleDomain);
			
			return cartDAORepository.save(cartDomain);
		}
		
		return null;
	}


	@Override
	public List<CartDomain> getCartDetails(String userId) throws Exception {
	UserDomain userDomain =	userDAORepository.findById(userId).get();
	return cartDAORepository.findByUserDomain(userDomain);		
			}


	@Override
	public String deleteCartItem(Integer cartId) {
		cartDAORepository.deleteById(cartId);
		return "Cart item deleted";
		}

	
	public void emptyCartByUserId(String userId) {
		
		cartDAORepository.emptyCartByUserId(userId);	
		
	}
	

	//@Override
	public CartModel getProjectDetails(long projectId, String userId) {
		// buy a single project (need to  check for book id and articleId )
		//if (isSingleProductCheckout && projectId != 0) {
			if (projectId != 0) {
			List<UploaderDomain> list = new ArrayList<>();
			CartModel cartModel = new CartModel();
			UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
			list.add(uploaderDomain);
			List<UploaderModel> uploaderModelList = uploaderMapper.entityList(list);
			cartModel.setUploaderModels(uploaderModelList);
			return cartModel;
		} else {
			
			UserDomain userDomain = userDAORepository.findById(userId).get();
			List<CartDomain> cartDomain = cartDAORepository.findByUserDomain(userDomain);
			List<UploaderModel> projectList = new ArrayList<>();
			List<UploaderArticleModel> articleList = new ArrayList<>();
			List<UploaderBookModel> bookList = new ArrayList<>();
			
			CartModel cartModel = new CartModel();
			for (CartDomain cartItem : cartDomain) {
				UploaderModel uploaderModel = new UploaderModel();
				UploaderArticleModel uploaderArticleModel = new UploaderArticleModel();
				UploaderBookModel uploaderBookModel = new UploaderBookModel();
				UploaderDomain UploaderDomain = new UploaderDomain();
				UploaderArticleDomain UploaderArticleDomain = new UploaderArticleDomain();
				UploaderBookDomain UploaderBookDomain = new UploaderBookDomain();
				if (cartItem.getProjectId() == 0) {
					UploaderDomain = null;
				} else {
					UploaderDomain = uploaderRepository.findProjectByProjectId(cartItem.getProjectId());
					BeanUtils.copyProperties(UploaderDomain, uploaderModel);
				}
				if (cartItem.getArticleId() == 0) {
					UploaderArticleDomain = null;
				} else {
					UploaderArticleDomain = uploaderArticleRepository.findByArticleId(cartItem.getArticleId());
					BeanUtils.copyProperties(UploaderArticleDomain, uploaderArticleModel);
				}
				if (cartItem.getBookId() == 0) {
					UploaderBookDomain = null;
				} else {
					UploaderBookDomain = uploaderBookRepository.findBookByBookId(cartItem.getBookId());
					BeanUtils.copyProperties(UploaderBookDomain, uploaderBookModel);
				}
				projectList.add(uploaderModel);
				articleList.add(uploaderArticleModel);
				bookList.add(uploaderBookModel);
								
			}
			cartModel.setUploaderModels(projectList);
			cartModel.setUploaderArticleModels(articleList);
			cartModel.setUploaderBookModels(bookList);
			return cartModel;
		}

	}

	/*@Override
	public List<CartDomain> getDetailsByCartId(Integer cartId) {
		List<CartDomain>cartDomainList = cartDAORepository.findByCartId(cartId);
		return cartDomainList;
		 
	}*/

	


	}



