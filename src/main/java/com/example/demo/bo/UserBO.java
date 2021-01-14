package com.example.demo.bo;

import com.example.demo.dao.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class UserBO {

	@Autowired
	UserRepo repo;
	
}
