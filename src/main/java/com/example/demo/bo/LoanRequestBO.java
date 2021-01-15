package com.example.demo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LoanRequestRepo;

@Component
public class LoanRequestBO {

	@Autowired
	LoanRequestRepo repo;
	
}
