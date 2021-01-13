package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bo.CreditCardRequestBO;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class CreditCardRequestController {

	@Autowired
	CreditCardRequestRepo repo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CreditCardRequestBO bo;
  
	//Story32
	@GetMapping(path="/creditCardRequests/dateRange")
	public Integer requestsByDateRange(@RequestParam String start, @RequestParam String end) {
		return repo.findByRequestTime(start, end).size();
	}
	
	@GetMapping(path="/creditCardRequests/rejected")
	public String findAllCountRejectedAndReason(){
		Map<String, Integer> ccrMap = bo.findAllCountRejectedAndReason();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		
		return gson.toJson(ccrMap);
//		return repo.findAll();
	}
	
	//Story 35
	@GetMapping(path="/creditCardRequests/approvals/regionProfession")
	public Integer approvalsProfessionRegion(@RequestParam String profession, @RequestParam String region){
		
		List<CreditCardRequest> approvals = new ArrayList<>();
		if (!region.isEmpty() && !profession.isEmpty()) {
			System.out.println("Both");
			approvals = repo.findByUserRegionAndUserProfession(region, profession);
		}
		else if (!region.isEmpty()) {
			approvals = repo.findByUserRegion(region);
		}
		else if (!profession.isEmpty()) {
			approvals = repo.findByUserProfession(profession);
		}
		return approvals.size();
	}

	/*
	 * This route is responsible for creating a credit card request.
	 * It accepts two params cardType and userId.
	 * On creation, a credit card request processes the card type requested with credit score on file.
	 * Status is set depending on whether credit score is high enough for card requested.
	 * Credit Card Request object is returned. 
	 */
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
