package com.example.demo.bo;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class CreditCardRequestBO {

	@Autowired
	CreditCardRequestRepo repo;
	
	/*
	 * converts returned List from repo to a map
	 * sends to controller in map format to be converted to JSON with GSON
	 */
	public Map<String, Integer>findAllCountRejectedAndReason(){
		List<CreditCardRequest> ccrList= repo.findAllByStatus("rejected");
		
		Map<String, Integer> ccrMap = new TreeMap<>();
		
		for(CreditCardRequest ccr: ccrList) {
			if(ccrMap.containsKey(ccr.getReason())) {
				Integer value = ccrMap.get(ccr.getReason());
				ccrMap.put(ccr.getReason(), ++value);
			} else {
				ccrMap.put(ccr.getReason(), 1);
			}
		}
		return ccrMap;
	}
	
	
	/*Story 42
	 * Takes a list of requests and returns the average time'
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Integer getTimeAvg() {
		System.out.println("sammy : CreditCardRequestBO/getTimeAvg()");
		
		//Grab all requests
		List<CreditCardRequest> requests = repo.findAll();
		List<Integer> times = new ArrayList<Integer>();
		Integer totalTimes = 0;
		
		//Traverse all requests
		for (CreditCardRequest x: requests) {
			
			//Check if times are not null
			if (x.getRequestTime() != null && x.getLastUpdated() != null) {
				
				Integer request = x.getRequestTime().getMinutes();
				Integer lastUpdate = x.getLastUpdated().getMinutes();
				
				times.add(lastUpdate - request);
			}
			else {
				System.out.println("Request Time is null");
			}
		}
		
		for (Integer x: times) {
			System.out.println(x);
			totalTimes =+ x;
			System.out.println(totalTimes);
		}
		
//		totalTimes = totalTimes/times.size();
		
		return totalTimes;
	}
	
}
