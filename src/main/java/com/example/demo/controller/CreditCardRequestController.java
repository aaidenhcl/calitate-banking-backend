package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;

@RestController
public class CreditCardRequestController {
	
	@Autowired
	CreditCardRequestRepo repo;
	
	
	//Get Status of CreditCardRequests
	@GetMapping(path="/creditCardRequests/status")
	public List<CreditCardRequest> getRequest() {
		
		//Calls Repo method.
		List<CreditCardRequest> allRequests = repo.findAllStatus();
		return allRequests;
	}

}
