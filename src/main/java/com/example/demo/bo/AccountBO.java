package com.example.demo.bo;

import com.example.demo.dao.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class AccountBO {

	@Autowired
	AccountRepo repo;
	
}
