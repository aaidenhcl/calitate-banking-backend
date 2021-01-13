package com.example.demo.bo;

import com.example.demo.dao.CreditCardRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCardBO {

	@Autowired
	CreditCardRepo repo;
	
	
}
