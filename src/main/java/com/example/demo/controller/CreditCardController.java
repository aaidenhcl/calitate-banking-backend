package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.utilities.DevUtil;
import com.example.demo.bo.CreditCardBO;
import com.example.demo.dao.CreditCardRepo;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.SpendRepo;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.springframework.web.bind.annotation.RestController;

//Story 37 import
import com.example.demo.model.Spend;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class CreditCardController {

	@Autowired
	CreditCardRepo repo;
	
	@Autowired
	CreditCardRequestRepo ccrRepo;
	
	@Autowired
	SpendRepo spendRepo;
	
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
	public CreditCard createCreditCards(@RequestParam Long ccrId, @RequestParam String status, @RequestHeader("Authorization") String token) throws NotAuthorizedException {
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
		
		throw new NotAuthorizedException("User is not authorized");
	}
	
	

	
	
	//Samiylo - Story36
	//A Credit card can view statements
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path= "/creditCards/spends")
	
	//Grab creditCard id from route
	public List<Spend> getStatement(@RequestHeader("cardNo") String cardNo, @RequestHeader("Authorization") String token) throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {												
			//Test
			System.out.println("samiylo - CreditCardController/getStatement()");
			System.out.println(cardNo);
			
			CreditCard history = bo.findByCreditCardNumber(cardNo);

			List<Spend> spends = history.getSpendHistory();
			
			
			//I want to return a list of spends for specific credit card
			return  spends;
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	

	/*
	 * returns map of categories with total accumulated amount
	 * as value
	 */
	@GetMapping(path="/creditCards/patterns")
	public String analyzeSpendingPatterns(@RequestHeader("cardNo") String cardNo, @RequestHeader("Authorization") String token) throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {			
			CreditCard creditCard = bo.findByCreditCardNumber(cardNo);
			Map<String, Double> spendsMap = bo.categorizeSpendsByAmount(creditCard);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(spendsMap);
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	
	/*
	 * Similar to analyzeSpendingPatterns but maps categories to percentage of use
	 */
	@GetMapping(path="/creditCards/patterns/stats")
	public String analyzeSpendingPatternsStats(@RequestHeader("cardNo") String cardNo, @RequestHeader("Authorization") String token) throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
			CreditCard creditCard = bo.findByCreditCardNumber(cardNo);
			Map<String, Double> spendsMap = bo.categorizeSpendsByStats(creditCard);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(spendsMap);
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	/*
	 * Samiylo
	 * Story 44, grab expiration dates that expire within 3 months
	 */
	@GetMapping(path="/creditCards/expiration")
	public List<CreditCard> getExperations(@RequestHeader("Authorization") String token) {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {
			System.out.println("sammy : CreditCardController/getExperations()");
			
			List<CreditCard> expiring = bo.getPendingExpirations();
			
			return expiring;
		}
		else {
			return null;
		}
		
	}

}
