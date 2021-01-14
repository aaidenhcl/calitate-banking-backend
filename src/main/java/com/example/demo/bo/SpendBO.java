package com.example.demo.bo;

import com.example.demo.dao.SpendRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class SpendBO {

	@Autowired
	SpendRepo repo;
	
	
	
}
