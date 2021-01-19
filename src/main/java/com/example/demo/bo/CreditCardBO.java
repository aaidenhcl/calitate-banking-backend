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
import java.util.concurrent.TimeUnit;

import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.Spend;
import com.example.demo.model.User;
import com.example.demo.service.CreditCardDiscontinued;
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
	
	
	public CreditCard findByCreditCardNumber(String creditCardNumber) {
		return repo.findByCreditCardNumber(creditCardNumber);
	}
	

	public CreditCard findById(Long id) {
		Optional<CreditCard> creditCardOpt = repo.findById(id);
		CreditCard creditCard = null; 
		if(creditCardOpt != null) {
			creditCard = creditCardOpt.get();
		}
		return creditCard;
	}
	
	//method is called from controller and calls repo method to return list that has credit cards that are discontinued 
	public List<CreditCardDiscontinued> getDiscontinued(){
		List<CreditCardDiscontinued> ccdList = repo.getDiscontinued();
		return ccdList;
	};
	
	/*
	 * calls BO to map values of accumulated amounts to categories
	 * maps category to accumulated amount
	 */
	public Map<String, Double> categorizeSpendsByAmount(CreditCard creditCard) {
		Map<String, Double> ccMap = new TreeMap<>();
		for(Spend spend: creditCard.getSpendHistory()) {
			if(ccMap.containsKey(spend.getCategory() + " total cost")) {
				Double value = ccMap.get(spend.getCategory() + " total cost");
				ccMap.put(spend.getCategory() + " total cost", spend.getAmount()+value);
			} else {
				ccMap.put(spend.getCategory()  + " total cost", spend.getAmount());
			}
		}
		return ccMap;
	}

	//method is called from controller and calls repo method to return List of region sales
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
			  
			  ccMap.put(key+" by percent", value/sum*100);
		}
		return ccMap;
	}
	
	
	/*
	 * Samiylo
	 * Story 44
	 * Grabs all credit cards, then returns list of credit cards
	 * that expire within 3 months
	 */
	public Map<String, Object> getPendingExpirations() {
		
		//Grab all Credit Cards
		List<CreditCard> all = repo.findAll();
		Map<String, Object> expirationMap = new TreeMap<>();
		
		//Create local calendar with and instantiate current time
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());

        
		
		//For each credit card
		for (CreditCard x : all) {
			if (x.getExpirationDate() != null) {
			
				Date expiration = x.getExpirationDate();
				
				/*
				 * Add 3 months to current date and check to see
				 * if its before the expiration date
				 */
				localCalendar.add(Calendar.MONTH, 3);
				Date currentTime = localCalendar.getTime();
				if (currentTime.before(expiration)) {
				
					System.out.println("Not expiring in 3 months");
			
				}
				else {
					
					/*
					 * If CC is expiring within 3 months, then add it to the map
					 */
					long diffInMili = Math.abs(expiration.getTime() - new Date().getTime());
					long diff = TimeUnit.DAYS.convert(diffInMili, TimeUnit.MILLISECONDS);
					
					//Style the response
					String strip = "Credit Card: ************" + x.getCreditCardNumber().substring(12);
					String expiringIn = "Expiring in " + diff + " days";
					expirationMap.put(strip , expiringIn);
				}
				
			}
		}
		
		return expirationMap;
	}
}
