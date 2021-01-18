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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bo.CreditCardRequestBO;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;
import com.example.demo.service.CreditCardDiscontinued;
import com.example.demo.utilities.DevUtil;
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
	public Integer requestsByDateRange(@RequestParam(value="start") String start, @RequestParam(value="end") String end, @RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		System.out.println("HITTING");
		System.out.println("START:: " + start);
		System.out.println("END::: " + end);
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {	
				return repo.findByRequestTime(start, end).size();				
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	@GetMapping(path="/creditCardRequests/rejected")
	public String findAllCountRejectedAndReason(@RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {														
			Map<String, Integer> ccrMap = bo.findAllCountRejectedAndReason();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(ccrMap);
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	//Story 35
	@GetMapping(path="/creditCardRequests/approvals/regionProfession")
	public Integer approvalsProfessionRegion(@RequestParam String profession, @RequestParam String region, @RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {											
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
		throw new NotAuthorizedException("User is not authorized");
	}

	/*
	 * This route is responsible for creating a credit card request.
	 * It accepts two params cardType and userId.
	 * On creation, a credit card request processes the card type requested with credit score on file.
	 * Status is set depending on whether credit score is high enough for card requested.
	 * Credit Card Request object is returned. 
	 */
	@PostMapping(path="creditCardRequests")
	public CreditCardRequest createCreditCardRequest(@RequestParam String cardType, @RequestParam Long user, @RequestHeader("Authorization") String token)  throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {														
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
		throw new NotAuthorizedException("User is not authorized");
	}
	
	//Get Status of CreditCardRequests
	@GetMapping(path="/creditCardRequests/status")
	public List<Map<String, Object>> getRequest(@RequestHeader("Authorization") String token) throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {		
			//Calls Repo method.
			List<CreditCardRequest> allRequests = repo.getStatusList();
			List<Map<String, Object>> response = bo.styleResponse(allRequests);
			
			return response;
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	
	/*Story 42
	 * This route is responsible for providing the average time all requests take
	 * Will have to set a minimum wait time
	 */
	@GetMapping(path="/creditCardRequests/average")
	public Long getAverageRequestTime(@RequestHeader("Authorization") String token) throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {
			System.out.println("sammy : CrediCardRequestController/getAverageRequestTime()");
			Long time = bo.getTimeAvg();
			System.out.println("Time in minutes: " + time);
		
			return time;
		}
		throw new NotAuthorizedException("User is not authorized");
	}
}
