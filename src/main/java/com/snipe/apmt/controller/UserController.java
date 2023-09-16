package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.utils.CommonUtils;

//@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/save/user")
	public ResponseEntity<GenericRes> saveUserInfo(@RequestBody UserModel userModel) throws Exception {
		return prepareSuccessResponse(userService.saveUserInfo(userModel));
	}

	@GetMapping(value = "/user/roleId/status/employeeStatus/{roleId}/{status}/{employeeStatus}")
	public ResponseEntity<GenericRes> getUserDetailByRoleId(@PathVariable("roleId") int roleId,
			@PathVariable("status") boolean status, @PathVariable("employeeStatus") boolean employeeStatus)
			throws Exception {
		return prepareSuccessResponse(userService.getUserDetailByRoleId(roleId, status, employeeStatus));
	}

	@GetMapping(value = "/userById/{id}/{status}")
	public ResponseEntity<GenericRes> getUserById(@PathVariable("id") String id, @PathVariable("status") boolean status)
			throws Exception {
		return prepareSuccessResponse(userService.getUserById(id, status));
	}

	@PutMapping(value = "/update/user/status")
	public ResponseEntity<GenericRes> updateStatus(@RequestBody UserDomain userDomain) throws Exception {
		return prepareSuccessResponse(userService.updateStatus(userDomain));
	}
	
	//Authorization
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<GenericRes> deleteProfileById(@PathVariable("id") String id) throws Exception {
		return prepareSuccessResponse(userService.deleteUser(id));
	}

	@PostMapping(value = "/login")
	public ResponseEntity<GenericRes> login(@RequestBody UserModel userModel) throws Exception {
		UserDomain user = userService.login(userModel);
		HttpSession session = CommonUtils.geSsession();
		if(session!=null)
		session.setAttribute("userModel", user);
		
		return prepareSuccessResponse(user);
	}
	
	@PostMapping(value = "/oauth2/login")
	public  ResponseEntity<GenericRes> oauthLogin(@RequestBody UserModel userModel) throws Exception {
			return prepareSuccessResponse(userService.oauthLogin(userModel));
		
	}

	@GetMapping(value = "/active/user/count/role/{roleId}/{status}")
	public ResponseEntity<GenericRes> getActiveUserCount(@PathVariable("roleId") int roleId,@PathVariable("status") boolean status) throws Exception {
		return prepareSuccessResponse(userService.getUserCount(roleId, status));
	}

	@GetMapping(value = "/inactive/user/count/role/{roleId}/{status}")
	public ResponseEntity<GenericRes> getInactiveUserCount(@PathVariable("roleId") int roleId,@PathVariable("status") boolean status) throws Exception {
		return prepareSuccessResponse(userService.getUserCount(roleId, status));
	}
	
	@GetMapping(value ="/generateOtp/{id}")
	/*public ResponseEntity<GenericRes> generateOTP(@RequestParam(value="userId") String userId) throws MessagingException{	
		return prepareSuccessResponse(userService.generateOTP(userId));
	}*/
	
	public ResponseEntity<GenericRes> generateOTP(@PathVariable("id") String id) throws MessagingException{	
		return prepareSuccessResponse(userService.generateOTP(id));
	}
	
	@GetMapping(value ="/validateOtp/{id}")
	public ResponseEntity<GenericRes> validateOTP(@RequestParam(value="otpnum") int otpnum,@PathVariable("id") String id) throws MessagingException{	
		return prepareSuccessResponse(userService.validateOTP(otpnum,id));
	}
}
 