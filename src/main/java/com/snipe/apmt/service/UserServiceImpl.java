package com.snipe.apmt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.snipe.apmt.common.CommonApi;
import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Role;
import com.snipe.apmt.constants.UserVerifiedStatus;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.dao.UserLogRepository;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.UserLogDomain;
import com.snipe.apmt.domain.VerifiedUserDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.exception.APMTRestException.ROLE_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.USER_NOT_FOUND;
import com.snipe.apmt.exception.APMTRestException.VALIDATE;
import com.snipe.apmt.filter.JwtToken;
import com.snipe.apmt.mapper.UserMapper;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.utils.DataValidation;
import com.snipe.apmt.utils.IDGeneration;
import com.snipe.apmt.utils.UserUtils;
import com.snipe.apmt.verification.service.IEmailService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired(required = true)
	DataValidation dataValidation;

	@Autowired
	JwtToken jwtToken;

	@Autowired
	APMTProperties arsProperties;

	@Autowired
	UserUtils userUtils;

	@Autowired
	CommonApi commonApi;

	@Autowired
	UserCommonService AgentCommonService;

	@Autowired
	UserCommonService userCommonService;

	@Autowired
	UserDAORepository userDAORepository;

	@Autowired
	UserLogRepository userLogRepository;

	@Autowired
	IDGeneration idGeneration;
	
	//@Autowired
	//UserVerifiedDAORepository userVerifiedDAORepository;
		
	@Autowired
	JavaMailSender mailSender;
				
	@Autowired
	IEmailService emailService;
	
	
	@Autowired
	VerifiedUserService verifiedUserService;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final Integer EXPIRE_MINS = 5;
	
    private LoadingCache<String, Integer> otpCache;

	@Override
	public UserModel saveUserInfo(UserModel userModel) throws Exception {
		userUtils.validateUserInfo(userModel);
		UserDomain userDomain = userUtils.isUserExistsByMobileOrEmail(userModel);
		if (userDomain == null)
			userDomain = new UserDomain();
		Role role = Role.getRole(userModel.getRoleId() + "");
		if (null == role)
			throw new ROLE_NOT_FOUND(userModel.getRoleId() + "");
		switch (role) {
		case ADMIN:
		case PURCHASER:
		case STUDENT:
		case EMPLOYEE:
		case VERIFICATION_MANAGER:
		case SALES_MANAGER:
			userModel = userCommonService.save(userModel, userDomain);
			break;
		default:
			break;
		}
		return userModel;
	}

	@Override
	public List<UserModel> getUserDetailByRoleId(int roleId, boolean status, boolean employeeStatus) throws Exception {
		List<UserDomain> userdomain = new ArrayList<UserDomain>();
		try {
			userdomain = userDAORepository.findByRoleIdAndStatusAndEmployeeStatus(roleId, status, employeeStatus);
		} catch (Exception e) {
			logger.error("Exception created in UserServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}
		List<UserModel> usermodel = new ArrayList<UserModel>();

		for (UserDomain user : userdomain) {
			UserModel userM = new UserModel();
			BeanUtils.copyProperties(user, userM);
			if (user.getAddressDomain() != null && user.getBankDomain() != null) {
				userM.setCountry(commonApi.getCountryName(user.getAddressDomain().getCountryId()));
				userM.setState(commonApi.getStateName(user.getAddressDomain().getStateId()));
				// userM.setCity(commonApi.getCityName(user.getAddressDomain().getCityId()));
				// userModel.setRoleId(userDomain.getRoleDomain().getRoleId());
			}

			userM.setRoleId(user.getRoleDomain().getRoleId());
			usermodel.add(userM);

		}
		return usermodel;
	}

	@Override
	public UserModel getUserById(String id, boolean status) throws Exception {
		UserDomain userDomain = new UserDomain();
		if (id == null || id.isEmpty())
			throw new VALIDATE("Please Enter UserId to get Info by UserId");
		try {
			userDomain = userDAORepository.findByIdOrStatus(id, status);
		} catch (Exception e) {
			logger.error("Exception created in UserServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDomain, userModel);
		if (userDomain.getAddressDomain() != null && userDomain.getBankDomain() != null) {
			userModel.setCountry(commonApi.getCountryName(userDomain.getAddressDomain().getCountryId()));
			userModel.setState(commonApi.getStateName(userDomain.getAddressDomain().getStateId()));
			userModel.setRoleId(userDomain.getRoleDomain().getRoleId());
			userModel.setAddress1(userDomain.getAddressDomain().getAddress1());
			userModel.setPinCode(userDomain.getAddressDomain().getPinCode());
			userModel.setAccountNumber(userDomain.getBankDomain().getAccountNumber());
			userModel.setBranchName(userDomain.getBankDomain().getBranchName());
			userModel.setPanNumber(userDomain.getBankDomain().getPanNumber());
			userModel.setIfscCode(userDomain.getBankDomain().getIfscCode());
			// userModel.setCity(commonApi.getCityName(userDomain.getAddressDomain().getCityId()));
		}

		return userModel;
	}

	@Override
	public String updateStatus(UserDomain userDomain) throws Exception {
		try {
			userDAORepository.save(userDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in UserService: ", e.getMessage());

		}
		return "updated";
	}

	@Override
	public String deleteUser(String id) throws Exception {
		if (id == null || id.isEmpty())
			throw new VALIDATE("Please Enter User Id to Delete User Info");
		UserDomain userDomain = new UserDomain();
		userDomain = userDAORepository.findByIdOrStatus(id, false);
		userDomain.setStatus(false);
		try {
			userDAORepository.save(userDomain);
		} catch (DataIntegrityViolationException e) {

			throw new DUPLIACATE_ENTRY_FOUND();
		} catch (Exception e) {
			logger.error("Exception created in UserService: ", e.getMessage());

		}

		return "DELETED SUCCESSFULLY";
	}

	@Override
	public UserDomain login(UserModel userModel) throws Exception {
		UserDomain userInfo = userDAORepository.findByEmailId(userModel.getEmailId());
		if (userInfo == null) {
			System.out.println("passowrd Email");
			throw new USER_NOT_FOUND();
		}
//		System.out.println(userDetails.getAuthorities());
		boolean isValidPwd = checkPassword(userInfo, userModel.getPassword());
		if (isValidPwd == true) {
			userInfo.setId(userInfo.getId());
			userInfo.setAccessToken(jwtToken
					.generateToken(userInfo.getId() + ":" + userInfo.getEmailId() + ":" + userInfo.getMobileNumber()));
			String timeExpires = timeExpireDate();
			userInfo.setTokenExpires(timeExpires);
			return userInfo;
		} else {
			System.out.println("passowrd Incorrect");
			throw new USER_NOT_FOUND();

		}
	}

	@Override
	public UserDomain oauthLogin(UserModel userModel) throws Exception {
		String email = userModel.getEmailId();

		UserDomain userInfo;
		userInfo = userDAORepository.findByEmailId(email);
		UserDomain userInfo1 = new UserDomain();
		if (userInfo == null) {
			String email1 = userModel.getEmailId();
			long mobileNumber = userModel.getMobileNumber();
			String firstName = userModel.getFirstName();
			String lastName = userModel.getLastName();

			userInfo1.setEmailId(email1);
			if (mobileNumber == 0) {
				userInfo1.setMobileNumber(1234567890);
			} else {
				userInfo1.setMobileNumber(mobileNumber);
			}
			userInfo1.setFirstName(firstName);
			userInfo1.setFirstName(lastName);
			userInfo1.setStatus(true);
			userInfo1.setId(idGeneration.generateRandomId());
			userDAORepository.save(userInfo1);
			userInfo1.setAccessToken(jwtToken.generateToken(
					userInfo1.getId() + ":" + userInfo1.getEmailId() + ":" + userInfo1.getMobileNumber()));
			String timeExpires;
			timeExpires = timeExpireDate();
			userInfo1.setTokenExpires(timeExpires);
			return userInfo1;
		}
		userInfo.setAccessToken(jwtToken
				.generateToken(userInfo.getId() + ":" + userInfo.getEmailId() + ":" + userInfo.getMobileNumber()));
		String timeExpires = timeExpireDate();
		userInfo.setTokenExpires(timeExpires);
		return userInfo;
	}

	private boolean checkPassword(UserDomain userInfo, String password) {
		boolean isValid = false;
		if (userInfo != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			isValid = passwordEncoder.matches(password, userInfo.getPassword());
		}
		return isValid;
	}

	private String timeExpireDate() throws Exception {
		String afteroneHour = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = formatter.parse(simpleDateFormat.format(new Date()).toString());
		TimeZone tzInAmerica = TimeZone.getTimeZone("Asia/Calcutta");
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date);
		calendar1.setTimeZone(tzInAmerica);
		//calendar1.add(Calendar.HOUR,1);
		calendar1.add(Calendar.DATE,1);
		Date date6 = formatter.parse(formatter.format(calendar1.getTime()).toString());
		SimpleDateFormat formatter_12_hours = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss aa");
		afteroneHour = formatter_12_hours.format(date6);
		return afteroneHour;
	}

	@Override
	public long getUserCount(int roleId, boolean status) throws Exception {
		UserModel user = new UserModel();
		try {
			user.setCount(userDAORepository.getUserCount(roleId, status));
		} catch (Exception e) {
			logger.error("Exception created in UserServiceImpl: ", e.getMessage());
			throw new BACKEND_SERVER_ERROR();
		}

		return user.getCount();
	}

	public void saveUserLogDetails(UserDomain userDomain, String operation, String projectTitle) {
		Optional<UserDomain> checkNull = Optional.ofNullable(userDomain);
		if (checkNull.isPresent()) {
			UserLogDomain userLogDomain = new UserLogDomain();
			userLogDomain.setUserId(userDomain.getId());
			userLogDomain.setAadharNumber(userDomain.getAadharNumber());
			userLogDomain.setAlternativeNumber(userDomain.getAlternativeNumber());
			userLogDomain.setDob(userDomain.getDob());
			userLogDomain.setFirstName(userDomain.getFirstName());
			userLogDomain.setLastName(userDomain.getLastName());
			userLogDomain.setEmailId(userDomain.getEmailId());
			userLogDomain.setMobileNumber(userDomain.getMobileNumber());
			userLogDomain.setUserActivity(operation);
			userLogDomain.setStatus(userDomain.isStatus());
			userLogDomain.setRoleId(userDomain.getRoleDomain().getRoleId());
			userLogDomain.setRoleName(userDomain.getRoleDomain().getRoleName());
			userLogDomain.setProjectTitle(projectTitle);
			userLogRepository.save(userLogDomain);
		}

	}
	
	public int generateOTP(String id) throws MessagingException{	
		  // UploaderDomain uploaderDomain = new UploaderDomain();
			//UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			Optional<UserDomain> userDomain = userDAORepository.findById(id);
			//req.getHeaders().
			String EmailTo = userDomain.get().getEmailId();
			int otp = genOTP(EmailTo);		
			emailService.sendOtpMessage("APMT",EmailTo,otp);
			return otp;
	}
	
	
	
	 public void sendSuccessEmail(String id) throws MessagingException {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		UserModel usermodel = new UserModel();
		//UserDomain userDomain= (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		Optional<UserDomain> userDomain = userDAORepository.findById(id);
		String EmailTo=userDomain.get().getEmailId();			
		emailService.sendWelcomeMessage("APMT",EmailTo);
		} 
 
	 public  UserServiceImpl(){    	
	      super();
	      otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
	      .build(new CacheLoader<String, Integer>() {
	      public Integer load(String key) {
	             return 0;
	       }
	     });
	   }
	 
	 public int genOTP(String key){
	    	Random random = new Random();
	    	int otp = 100000 + random.nextInt(900000);
	    	otpCache.put(key, otp);
	    	return otp;
	    	 }
	    
	    public int getOtp(String key){ 
	    	try{
	    	 return otpCache.get(key); 
	    	}catch (Exception e){
	    	 return 0; 
	    	}
	    }
	    
	    public void clearOTP(String key){ 
	    	 otpCache.invalidate(key);
	    	 }

		@Override
		public String validateOTP(int otpnum,String id) throws MessagingException {
			final String SUCCESS = "Entered Otp is valid";
			final String FAIL = "Entered Otp is NOT valid. Please Retry!";
			
			//UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			Optional<UserDomain> userDomain = userDAORepository.findById(id);
			String EmailTo=userDomain.get().getEmailId();	
			//Validate the Otp 
			if(otpnum >= 0){
				
			  int serverOtp = getOtp(EmailTo);
			    if(serverOtp > 0){
			      if(otpnum == serverOtp){
			          clearOTP(EmailTo);
			          sendSuccessEmail(id);
			         
			   VerifiedUserDomain verifiedUserDomain  =  verifiedUserService.saveVerifiedUser(id);
			 String verifiedStatusDesc =  verifiedUserDomain.getVerifiedStatusDesc();
	                  return verifiedStatusDesc ;
	                  
	                } 
			        else {
	                    return FAIL;
	                   }
	               }else {
	              return FAIL;
	               }
	             }else {
	                return FAIL;
	         }
	      }
	   
		
	
}



