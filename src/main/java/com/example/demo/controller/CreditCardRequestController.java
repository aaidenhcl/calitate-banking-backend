package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;

@RestController
public class CreditCardRequestController {

	@Autowired
	CreditCardRequestRepo repo;

	
	
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

	
}
