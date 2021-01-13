package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.bo.CreditCardBO;
import com.example.demo.dao.CreditCardRepo;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.SpendRepo;
import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.web.bind.annotation.RestController;

//Story 37 import
import com.example.demo.model.Spend;

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
	
	

	
	
	//Samiylo - Story36
	//A Credit card can view statements
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path= "/creditCards/{id}/spends")
	
	//Grab creditCard id from route
	public List<Spend> getStatement(@PathVariable("id") Long id) {
		
		//Test
		System.out.println("samiylo - CreditCardController/getStatement()");
		System.out.println(id);
		
		Optional<CreditCard> history = repo.findById(id);
//		List<Spend> list = spendRepo.getAllStatement(id);
		
		if (history == null) {
			return null;
		}
		
		List<Spend> spends = history.get().getSpendHistory();
	
		
		//I want to return a list of spends for specific credit card
		return  spends;
	}
	

	/*
	 * returns map of categories with total accumulated amount
	 * as value
	 */
	@GetMapping(path="/creditCards/{id}/patterns")
	public String analyzeSpendingPatterns(@PathVariable("id") Long id) {
		CreditCard creditCard = bo.findById(id);
		Map<String, Double> spendsMap = bo.categorizeSpendsByAmount(creditCard);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		return gson.toJson(spendsMap);
	}
	
	
	/*
	 * Similar to analyzeSpendingPatterns but maps categories to percentage of use
	 */
	@GetMapping(path="/creditCards/{id}/patterns/stats")
	public String analyzeSpendingPatternsStats(@PathVariable("id") Long id) {
		CreditCard creditCard = bo.findById(id);
		Map<String, Double> spendsMap = bo.categorizeSpendsByStats(creditCard);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		return gson.toJson(spendsMap);
	}

}
