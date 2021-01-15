package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.AccountBO;
import com.example.demo.dao.AccountRepo;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.utilities.DevUtil;

@RestController
public class AccountController {

	@Autowired
	AccountRepo repo;
	
	@Autowired
	AccountBO bo;
	
	@PostMapping(path="/account")
	public Account createUser(@RequestBody Account account, @RequestHeader("Authorization") String token) throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {								
			System.out.println(account);
			repo.save(account);
			return account;
		}		
		throw new NotAuthorizedException("User is not authorized");
	}
}
