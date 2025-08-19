package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.configs.JWTUtils;
import com.acropolis.entity.Users;
import com.acropolis.models.LoginModel;
import com.acropolis.models.LoginResponseModel;
import com.acropolis.models.UserModel;
import com.acropolis.services.UserService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("/money")
public class MoneyController {
	public static APIResponse resp;
	@Autowired
	private UserService userv;
	@Autowired
	private JWTUtils jwt;
	
	@PostMapping("/save")
	public ResponseEntity<APIResponse> save(@RequestBody UserModel model){
		 Users ob=userv.saveUser(model);
		 if(ob!=null) {
			 resp=new APIResponse("User added successfully...", true, ob);
			 return ResponseEntity.ok(resp);
		 }else {
			 resp=new APIResponse("User could not be added...", false, null);
			 return ResponseEntity.ok(resp);
		 }
	}
	
		@GetMapping("/custlist")
		public ResponseEntity<APIResponse> list(){
			List<Users> list=userv.list();
		 	 resp=new APIResponse("List of Customers", true,list);
			 return ResponseEntity.ok(resp);
		
		}
		
		@GetMapping("/wrongauth")
		public ResponseEntity<APIResponse> wrongauth(){
			 resp=new APIResponse("UnAuthorised user", true,null);
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		
		}
	
		 @PostMapping("/login")
		 public ResponseEntity<APIResponse> login(@RequestBody LoginModel model){
			 	Users ob=userv.checkLogin(model);
			 	if(ob!=null) {
			 	 String token=jwt.generateToken(ob.getEmail());
			 	 LoginResponseModel logModel=new LoginResponseModel(ob.getName(),ob.getRole(),token);
			 	 resp=new APIResponse("Welcome to app", true,logModel);
				 return ResponseEntity.ok(resp);
			 	}
			 	else {
			 		 resp=new APIResponse("Invalid Credentials....", false, null);
					 return ResponseEntity.ok(resp);
			 	}
		 }
		 
}
	
	
	

