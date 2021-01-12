package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;

@RestController
public class CreditCardRequestController {

	@Autowired
	CreditCardRequestRepo repo;

	//Story 31
	//untested
	@GetMapping(path="/creditCardRequests/requests/byDate")
	public List<CreditCardRequest> requestsByDate(@RequestHeader(value="startDate") Date startDate, @RequestHeader(value="endDate") Date endDate){
		
		return null;
		
	}
	
	//Story 34
	//untested
	@GetMapping(path="/creditCardRequests/requests/approvals/regionProfession")
	public Integer approvalsProfessionRegion(@RequestHeader(value="profession") String profession, @RequestHeader(value="region") String region){
		
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
		return approvals.size();
	}

	
}
