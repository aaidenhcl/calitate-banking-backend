package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.utilities.DevUtil;
import com.example.demo.bo.CreditCardBO;
import com.example.demo.dao.CreditCardRepo;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

	@Autowired
	CreditCardRepo repo;
	
	@Autowired
	CreditCardRequestRepo ccrRepo;
	
	@Autowired
	CreditCardBO bo;
	
	/*
	 * Hit this route to either accept or decline a credit card request offer
	 * queries the credit card request 
	 * credit card object is created and saved if request param == accepted
	 * credit card request acceptOffer method instantiates credit card
	 * credit card is saved and returned
	 */
	@PostMapping(path="/creditCards")
	public CreditCard createCreditCards(@RequestParam Long ccrId, @RequestParam String status, @RequestHeader("Authorization") String token) {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {									
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
		}
		
		return null;
	}
	
	
	/*
	 * returns map of categories with total accumulated amount
	 * as value
	 */
	@GetMapping(path="/creditCards/{id}/patterns")
	public String analyzeSpendingPatterns(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {			
			CreditCard creditCard = bo.findById(id);
			Map<String, Double> spendsMap = bo.categorizeSpendsByAmount(creditCard);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(spendsMap);
		}
		System.out.println("NOT AUTHENTICATED");
		return null;
	}
	
	
	/*
	 * Similar to analyzeSpendingPatterns but maps categories to percentage of use
	 */
	@GetMapping(path="/creditCards/{id}/patterns/stats")
	public String analyzeSpendingPatternsStats(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
			CreditCard creditCard = bo.findById(id);
			Map<String, Double> spendsMap = bo.categorizeSpendsByStats(creditCard);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(spendsMap);
		}
		return null;
	}
}
