package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.AVG;
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
	public Map<String, String> requestsByDateRange(@RequestParam String start, @RequestParam String end, @RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {		
			List<CreditCardRequest> requests = repo.findByRequestTime(start, end);
			Map<String, String> toReturn = new LinkedHashMap<>();
			toReturn.put("Number of Credit Card Requests received between "+start+" and "+end,""+requests.size());
			repo.findByRequestTime(start, end).stream().forEach(d -> toReturn.put("Request ID: "+d.getId(),"Date: "+d.getRequestTime().toString()));
			return toReturn;
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
	public Map<String, String> approvalsProfessionRegion(@RequestParam String profession, @RequestParam String region, @RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {											
			List<CreditCardRequest> approvals = new ArrayList<>();
			if (!region.isEmpty() && !profession.isEmpty()) {
				approvals = repo.findByUserRegionAndUserProfession(region, profession);
			}
			else if (!region.isEmpty()) {
				approvals = repo.findByUserRegion(region);
			}
			else if (!profession.isEmpty()) {
				approvals = repo.findByUserProfession(profession);
			}
			for (int i = 0; i < approvals.size(); i ++) {
				if (approvals.get(i).getStatus().equals("rejected"))
					approvals.remove(i);
			}
			Map<String, String> toReturn = new LinkedHashMap<>();
			toReturn.put("Number of Credit Card Approvals with Profession: " + (profession.isEmpty() ? "Not Specified" : profession)+" and Region: "+(region.isEmpty() ? "Not Specified" : region),""+approvals.size());
			approvals.stream().forEach(d -> toReturn.put("Request ID: "+d.getId(), "Username: "+d.getUser().getUsername()+" | Status: "+d.getStatus()));
			
			return toReturn;
			
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
	public Map<String, Object> getAverageRequestTime(@RequestHeader("Authorization") String token) throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {
			System.out.println("sammy : CrediCardRequestController/getAverageRequestTime()");
			Map<String, Object> minutes = new TreeMap<>();
			
			//Create respective time
			Long time = bo.getTimeAvg();
			Long hours = time/60;
			Long days = hours/12;
			
			//Add to responseMap only if greater than 0
			minutes.put("Avg. Time in Minutes: ", time);
			if(hours > 0 ) {
				minutes.put("Avg. Time in Hours", hours);
			}
			if (days > 0) {
				minutes.put("Avg. Time in Days", days);
			}
			
			return minutes;
		}
		throw new NotAuthorizedException("User is not authorized");
	}
}
