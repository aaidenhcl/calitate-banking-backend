package com.example.demo.controller;

import com.example.demo.model.CheckingAccount;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.CheckingAccountRepo;
import com.example.demo.model.ConsumerUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingAccountController {

	@Autowired
	CheckingAccountRepo repo;
	
	@PostMapping(path="/account")
	public CheckingAccount createUser(@RequestBody CheckingAccount account){
		System.out.println(account);
		repo.save(account);
		return account;
	}
	
}
