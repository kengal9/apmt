package com.snipe.apmt.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.salesmanager.constant.ProjectStatusCode;
import com.snipe.apmt.salesmanager.dao.PriceRepository;
import com.snipe.apmt.salesmanager.dao.SalesManagerRepository;
import com.snipe.apmt.salesmanager.domain.PriceDomain;
import com.snipe.apmt.salesmanager.domain.SalesManagerDomain;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.utils.CommonUtils;


@Service


public class SalesManagerServiceImpl implements SalesManagerService {

	@Autowired
	UploaderRepository uploaderRepository;
	
	@Autowired
	UploaderMapper uploaderMapper;
	
	@Autowired
	SalesManagerRepository salesRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceRepository priceRepository;	   

	@Override
	public List<UploaderModel> getallSalesProjects(int categoryID) throws Exception {
		List<UploaderDomain> projectDomain = uploaderRepository.getProjectsListByCategoryId(categoryID);
		return uploaderMapper.entityList(projectDomain);
	}

	@Override
	public List<UploaderModel> salesNewProjects() throws Exception {
		List<UploaderDomain> projectDomain = uploaderRepository.findProjectsByStatus(ProjectStatusCode.APPROVED.getCode());
		return uploaderMapper.entityList(projectDomain);
	}

	@Override
	public String editPrice (long project_id, long editPrice) throws Exception {
		// TODO Auto-generated method stub
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(project_id);
		PriceDomain priceDomain= new PriceDomain();									 
		long oldPrice = uploaderDomain.getPrice();
		long newPrice = editPrice;
		uploaderDomain.setEditPrice(newPrice);
		uploaderRepository.updatePrice(uploaderDomain.getEditPrice(),uploaderDomain.getProjectId());
		
		//insert into user activity log table
		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "Modified price", uploaderDomain.getTitle());
		
		SalesManagerDomain salesManagerDomain = new SalesManagerDomain();
		if (uploaderDomain != null) {
			
			salesManagerDomain.setProjectId(uploaderDomain.getProjectId());
			salesManagerDomain.setPrice(oldPrice);
			salesManagerDomain.setEditPrice(newPrice);
			salesRepository.save(salesManagerDomain);
		}
		
			/*long projectId = uploaderDomain.getProjectId();
			
			if(priceRepository.existsById(projectId))
			{
				
				priceDomain.setDiscount(discountPercent);
				//calculate discount price
				//discounted_price = original_price - (original_price * discount / 100)
				newPrice =  oldPrice - (oldPrice * priceDomain.getDiscount() /100);
				priceDomain.setEditPrice(newPrice);
			
				//priceRepository.update(priceDomain);
			
			}
			else {
			
			priceDomain.setProjectId(uploaderDomain.getProjectId());
			
			priceDomain.setPrice(oldPrice);
			priceDomain.setDiscount(discountPercent);
			//calculate discount price
			//discounted_price = original_price - (original_price * discount / 100)
			newPrice =  oldPrice - (oldPrice * priceDomain.getDiscount() /100);
			priceDomain.setEditPrice(newPrice);
		
			priceRepository.save(priceDomain);
			}*/
		//}
		return "PriceSaved";
	
	} 
}
	
	
	/*@Override
	public long editPrice(long project_id, long price) throws Exception {

		UploaderDomain uploaderDomain = uploaderRepository.findByProjectId(project_id);
		boolean projectVerifiedStatus = isProjectVerified(uploaderDomain);
		long projectPrice = isProjectPrice(uploaderDomain);*/

	
	
	

	/*
	 * @Override public List<UploaderModel> getVerifiedProjects() throws Exception {
	 * List<UploaderDomain> projectDomain =
	 * uploaderRepository.findProjectsByStatus(ProjectStatusCode.VERIFIED.getCode())
	 * ; // TODO Auto-generated method stub return
	 * uploaderMapper.entityList(projectDomain); }
	 * 
	 * @Override public List<UploaderModel> getNewProjects() throws Exception {
	 * List<UploaderDomain> projectDomain =
	 * uploaderRepository.findProjectsByStatus(ProjectStatusCode.NEW.getCode()); //
	 * TODO Auto-generated method stub return
	 * uploaderMapper.entityList(projectDomain); }
	 */

	/*
	 * @Override 
	 * public List<UploaderModel> getprojectsByCategory() throws Exception { 
	 * List<UploaderDomain> projectDomain = uploaderRepository.findProjectsByCategory();	 * 
	 * return uploaderMapper.entityList(projectDomain); 
	 * }
	 * 
	 * @Override public List<UploaderModel> getprojectsByCategoryID(int category) throws Exception {	 * 
	 * List<UploaderDomain> projectDomain = uploaderRepository.getprojectsByCategoryID(category);
	 *  return  uploaderMapper.entityList(projectDomain); }
	 */


