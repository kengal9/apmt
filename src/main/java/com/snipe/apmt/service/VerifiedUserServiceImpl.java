package com.snipe.apmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.constants.UserVerifiedStatus;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.dao.VerifiedUserRepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.VerifiedUserDomain;
import com.snipe.apmt.model.VerifiedUserModel;

@Service
public class VerifiedUserServiceImpl implements VerifiedUserService{

	@Autowired
	VerifiedUserRepository verifiedUserRepository;
	
	@Autowired
	UserDAORepository userDAORepository;
	
	@Override
	public List<VerifiedUserDomain> verifiedUsersList() {		
		List<VerifiedUserDomain> VerifiedUserDomain = verifiedUserRepository.getVerifiedUsersList();
		return VerifiedUserDomain;
	}

	public VerifiedUserDomain saveVerifiedUser(String userId) {
		
		Optional<UserDomain> userDomain = userDAORepository.findById(userId);
		String EmailTo=userDomain.get().getEmailId();
		VerifiedUserDomain verifiedUserDomain = new VerifiedUserDomain();
		verifiedUserDomain.setEmailId(EmailTo);
		verifiedUserDomain.setUserId(userId);
		verifiedUserDomain.setVerifiedStatusDesc(UserVerifiedStatus.VERIFIED.getDesc());
		verifiedUserDomain.setVerifiedStatusCode(UserVerifiedStatus.VERIFIED.getCode());
		verifiedUserRepository.save(verifiedUserDomain);
         
                 
		return verifiedUserDomain;
	}

	@Override
	public String checkUserVerified(String userId) {
		
		Optional<UserDomain> userDomain = userDAORepository.findById(userId);
		String verifiedStatusDes = verifiedUserRepository.checkUserVerified(userId);		
		//String Desc =  verifiedUserDomain.get().getVerifiedStatusDesc();
		return verifiedStatusDes;
	}

	
}

