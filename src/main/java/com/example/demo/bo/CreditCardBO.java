package com.example.demo.bo;

import com.example.demo.dao.CreditCardRepo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.example.demo.model.CreditCard;
import com.example.demo.model.Spend;
import com.example.demo.model.User;
import com.example.demo.service.RegionSale;
import com.example.demo.utilities.DevUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class CreditCardBO {

	@Autowired
	CreditCardRepo repo;
	
	


	public CreditCard findById(Long id) {
		Optional<CreditCard> creditCardOpt = repo.findById(id);
		CreditCard creditCard = null; 
		if(creditCardOpt != null) {
			creditCard = creditCardOpt.get();
		}
		return creditCard;
	}
	
	/*
	 * calls BO to map values of accumulated amounts to categories
	 * maps category to accumulated amount
	 */
	public Map<String, Double> categorizeSpendsByAmount(CreditCard creditCard) {
		Map<String, Double> ccMap = new TreeMap<>();
		for(Spend spend: creditCard.getSpendHistory()) {
			if(ccMap.containsKey(spend.getCategory())) {
				Double value = ccMap.get(spend.getCategory());
				ccMap.put(spend.getCategory(), spend.getAmount()+value);
			} else {
				ccMap.put(spend.getCategory(), spend.getAmount());
			}
		}
		return ccMap;
	}

	public List<RegionSale> getRegionSale(){	
			List<RegionSale> rs = repo.getRegionSale();
			return rs;
	}
	
	/*
	 * fetches credit card
	 * maps accumulated values to category keys
	 * calculates category percentages and returns new mapped percentage values
	 */
	public Map<String, Double> categorizeSpendsByStats(CreditCard creditCard){
		Map<String, Double> ccMap = new TreeMap<>();
		Double sum = 0d;
		for(Spend spend: creditCard.getSpendHistory()) {
			sum += spend.getAmount();
		}
		System.out.println("MADE IT!");
		Map<String, Double> ccMap2 = categorizeSpendsByAmount(creditCard);
		for(Map.Entry<String,Double> entry : ccMap2.entrySet()) {
			  String key = entry.getKey();
			  Double value = entry.getValue();
			  
			  ccMap.put(key, value/sum*100);
		}
		return ccMap;
	}
}
