package com.example.demo.bo;

import com.example.demo.dao.CreditCardRepo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.TreeMap;

import com.example.demo.model.CreditCard;
import com.example.demo.model.Spend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	/*
	 * Samiylo
	 * Story 44
	 * Grabs all credit cards, then returns list of credit cards
	 * that expire within 3 months
	 */
	public List<CreditCard> getPendingExpirations() {
		
		//Grab all Credit Cards
		List<CreditCard> all = repo.findAll();
		List<CreditCard> expiring = new ArrayList<>();
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentTime = localCalendar.getTime();
        
		
		//For each credit card
		for (CreditCard x : all) {
			if (x.getExpirationDate() != null) {
			
				Date expiration = x.getExpirationDate();
				//System.out.println(expiration);
				
				/*
				 * Add 3 months to current date and check to see
				 * if its before the expiration date
				 */
				localCalendar.add(Calendar.MONTH, 3);
				if (currentTime.before(expiration)) {
				
					System.out.println("Not expiring in 3 months");
				
					System.out.println(localCalendar.getTime());
				}
				else {
					
					System.out.println("Expiring within 3 months");
					expiring.add(x);
				}
				
			}
		}
		
		return expiring;
	}
}
