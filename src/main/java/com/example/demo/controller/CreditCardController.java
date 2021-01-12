package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.dao.CreditCardRepo;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

	@Autowired
	CreditCardRepo repo;
	
	@Autowired
	CreditCardRequestRepo ccrRepo;
	
	@PostMapping(path="/creditCards")
	public CreditCard createCreditCards(@RequestParam Long ccrId, @RequestParam String status) {
		
		Optional<CreditCardRequest> ccrOpt = ccrRepo.findById(ccrId);
		if(ccrId != null) {
			CreditCardRequest ccr = ccrOpt.get();
			System.out.println(ccr);
			if(status.equals("accepted")) {				
				CreditCard creditCard = ccr.acceptOffer();
				repo.save(creditCard);
				return creditCard;
			}
			else {
				ccr.declineOffer();
				return null;
			}
		}
		
		return null;
	}
	
	
}
