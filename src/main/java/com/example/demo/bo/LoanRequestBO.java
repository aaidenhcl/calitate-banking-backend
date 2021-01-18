package com.example.demo.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LoanRequestRepo;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.LoanRequest;

@Component
public class LoanRequestBO {

	@Autowired
	LoanRequestRepo repo;
	
	 /* Grabs list of all requests and styles it for a response
	 */
	public List<Map<String, Object>> styleResponse(List<LoanRequest> allRequests) {
		System.out.println("In style response");
		

		List<Map<String, Object>> all = new ArrayList<>();
		
		//Fill Map
		for (LoanRequest lr : allRequests) {
			LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
			
			//Add to object map
			responseMap.put("Request Id: ", lr.getId());
			responseMap.put("Status: ", lr.getStatus());
			responseMap.put("Offered Limit: ", lr.getOfferedAmount());
			responseMap.put("APR: ", lr.getOfferedApr());
			responseMap.put("Requested: ", lr.getRequestedTime());
			
			//Add object map to list
			all.add(responseMap);
		}
		
		
		
		return all;
	}
	
	/*
	 * converts returned List from repo to a map
	 * sends to controller in map format to be converted to JSON with GSON
	 */
	public Map<String, Integer>findAllCountRejectedAndReason(){
		List<LoanRequest> lrList= repo.findAllByStatus("rejected");
		
		Map<String, Integer> lrMap = new TreeMap<>();
		
		for(LoanRequest lr: lrList) {
			if(lrMap.containsKey(lr.getReason())) {
				Integer value = lrMap.get(lr.getReason());
				lrMap.put(lr.getReason(), ++value);
			} else {
				lrMap.put(lr.getReason(), 1);
			}
		}
		return lrMap;
	}
	
}
