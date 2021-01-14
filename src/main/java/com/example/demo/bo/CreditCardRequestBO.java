package com.example.demo.bo;

import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.model.CreditCardRequest;

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
	
}
