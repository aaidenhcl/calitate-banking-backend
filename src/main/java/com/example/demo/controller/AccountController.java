package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AccountRepo;
import com.example.demo.model.Account;

@RestController
public class AccountController {

		@Autowired
		AccountRepo repo;
		
		@PostMapping(path="/account")
		public Account createUser(@RequestBody Account account){
			System.out.println(account);
			repo.save(account);
			return account;
		}
}
