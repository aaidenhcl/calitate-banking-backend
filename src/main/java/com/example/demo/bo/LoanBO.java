package com.example.demo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LoanRepo;

@Component
public class LoanBO {
	
	@Autowired
	LoanRepo repo;
}
