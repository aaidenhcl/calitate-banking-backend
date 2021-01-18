package com.example.demo.bo;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.service.CreditCardDiscontinued;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

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
	 * Return average time to respond to all requests
	 */
	@SuppressWarnings("deprecation")
	public Long getTimeAvg() {
		System.out.println("sammy : CreditCardRequestBO/getTimeAvg()");
		
		//Grab all requests
		List<CreditCardRequest> requests = repo.findAll();
		List<Long> times = new ArrayList<Long>();
		Long totalTimes = (long) 0.0;
		
		//Traverse all requests
		for (CreditCardRequest x: requests) {
			
			//Check if times are not null
			if (x.getRequestTime() != null && x.getLastUpdated() != null) {
				
				//Test
				Date  testReq = x.getRequestTime();
				Date testLast = x.getLastUpdated();
				
				long diffInMili = Math.abs(testLast.getTime() - testReq.getTime());
				long diff = TimeUnit.MINUTES.convert(diffInMili, TimeUnit.MILLISECONDS);
				
				System.out.println(diff);
				
				//System.out.println("Request time: " + request);
				//System.out.println("Last Update Time: " + lastUpdate);
				
				times.add(diff);
			}
			else {
				//System.out.println("Request Time is null");
			}
		}
		
		
		for (Long x: times) {
			
			totalTimes += x;
			//System.out.println(totalTimes);
		}
		
		//Divide total hours by number of requests
		totalTimes = totalTimes/times.size();
		
		return totalTimes;
	}
	
}
