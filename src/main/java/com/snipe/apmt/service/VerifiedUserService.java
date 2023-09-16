package com.snipe.apmt.service;

import com.snipe.apmt.domain.VerifiedUserDomain;

public interface VerifiedUserService {

	public Object verifiedUsersList();

	//Object saveVerifiedUser();

	public  VerifiedUserDomain saveVerifiedUser(String userId);

	public String checkUserVerified(String userId);

	
}
