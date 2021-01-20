package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.LoanRequestBO;
import com.example.demo.dao.LoanRequestRepo;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.Loan;
import com.example.demo.model.LoanRequest;
import com.example.demo.model.User;
import com.example.demo.utilities.DevUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class LoanRequestController {

	@Autowired
	LoanRequestRepo repo;
	
	@Autowired
	LoanRequestBO bo;
	
	//Get Status of Loan Requests
	@GetMapping(path="loanRequests/status")
	public List<Map<String, Object>> getRequest(@RequestHeader("Authorization") String token) throws NotAuthorizedException {
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {		
			//Calls Repo method.
			List<LoanRequest> allRequests = repo.getStatusList();
			List<Map<String, Object>> response = bo.styleResponse(allRequests);
			
			return response;
		}
		
		throw new NotAuthorizedException("User is not authorized");
	}
	
	//Gets Loan Requests based on a region and/or a profession
	//and returns it to the client
	@GetMapping(path="/loanRequests/approvals/regionProfession")
	public Map<String, String> approvalsProfessionRegion(@RequestParam String profession, @RequestParam String region, @RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {											
			List<LoanRequest> approvals = new ArrayList<>();
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
			
			for (int i = 0; i < approvals.size(); i ++) {
				if (approvals.get(i).getStatus().equals("rejected"))
					approvals.remove(i);
			}
			
			Map<String, String> toReturn = new LinkedHashMap<>();
			toReturn.put("Number of Loan Approvals with Profession: " + (profession.isEmpty() ? "Not Specified" : profession)+" and Region: "+(region.isEmpty() ? "Not Specified" : region),""+approvals.size());
			approvals.stream().forEach(d -> toReturn.put("Request ID: "+d.getId(), "Username: "+d.getUser().getUsername()+", Status: "+d.getStatus()));
			
			return toReturn;
		}
		throw new NotAuthorizedException("User is not authorized");
	}
	
	@GetMapping(path="/loanRequests/rejected")
	public String findAllCountRejectedAndReason(@RequestHeader("Authorization") String token)  throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {														
			Map<String, Integer> lrMap = bo.findAllCountRejectedAndReason();
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			return gson.toJson(lrMap);
		}
		throw new NotAuthorizedException("User is not authorized");
	}

	
}
