package com.example.demo.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;

@RestController
public class CreditCardRequestController {

	@Autowired
	CreditCardRequestRepo repo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping(path="creditCardRequests")
	public CreditCardRequest createCreditCardRequest(@RequestParam String cardType, @RequestParam Long user) {
		System.out.println("body Request::: " + cardType);
		System.out.println("userID::: " + user);
		
		Optional<User> userOpt = userRepo.findById(user);
		if(userOpt != null) {
			User userFound = userOpt.get();
//			System.out.println(userFound);
			CreditCardRequest ccr = new CreditCardRequest(cardType, userFound);
			repo.save(ccr);
			return ccr;
		}
		return null;
	}
	
	//Get Status of CreditCardRequests
	@GetMapping(path="/creditCardRequests/status")
	public List<CreditCardRequest> getRequest() {
		
		//Calls Repo method.
		List<CreditCardRequest> allRequests = repo.getStatusList();
	
		
		return allRequests;
	}
}
