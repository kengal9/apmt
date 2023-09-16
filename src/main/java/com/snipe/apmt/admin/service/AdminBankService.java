package com.snipe.apmt.admin.service;

import java.util.List;





import com.snipe.apmt.admin.model.AdminBankModel;

public interface AdminBankService {

	public String adminAddBank(AdminBankModel adminBankModel) throws Exception;

	public List<AdminBankModel> getAdminBankList() throws Exception;

	public AdminBankModel getAdminBankListById(String adminBankId) throws Exception;

	//public AdminBankModel getAdminBankListByname(String adminBankName) throws Exception;

	
	public String adminUpdateBank(AdminBankModel adminBankModel) throws Exception;

	public int adminDeleteBankById(String adminBankId) throws Exception;

	public long adminBankCount() throws Exception;

}