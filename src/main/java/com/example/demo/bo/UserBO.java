package com.example.demo.bo;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.CreditCard;

import com.example.demo.model.Spend;
import com.example.demo.model.User;

import com.example.demo.service.AgeDemographics;
import com.example.demo.service.DemographicsProfession;
import com.example.demo.service.DemographicsRegion;
import com.example.demo.utilities.DevUtil;
import com.example.demo.service.RegionSale;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.demo.model.Payment;
import com.example.demo.model.Spend;
import com.example.demo.model.User;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class UserBO {

	@Autowired
	UserRepo repo;
	
	
	public User findByUsername(String username) {
		return repo.findByUsername(username).get(0);
	}
	
	public Double findTotalCreditLimitByUsername(String username) {
		User foundUser = findByUsername(username);
		double totalLimit = 0.0;
		for (CreditCard cc : foundUser.getCreditCards())
			if (cc.getStatus().equals("active"))
				totalLimit += cc.getSpendingLimit();
		return totalLimit;
	}


	public User findById(Long id) {
		Optional<User> userOpt = repo.findById(id);
		if(userOpt != null) {
			User user = userOpt.get();
			return user;
		}
		return null;
	}
	
	//method called from controller and calls repo method to return List of amount users based on professions
	public List<DemographicsProfession> getDemographicsProfession(){
			List<DemographicsProfession> dpl = repo.getDemographicsProfession();
			return dpl;
	}
	
	//method called from controller and calls repo method to return List of users based on regions
	public List<DemographicsRegion> getDemographicsRegion(){
		List<DemographicsRegion> drl = repo.getDemographicsRegion();
		return drl;
   }	
	
	//method called from controller and calls repo method to return List of user ages
	public List<User> getDemographicsAge(Integer lower, Integer upper){
		List<User> ul = repo.getDemographicsAge(lower, upper);
		return ul;
	}
	
	
	//method return map of <region, users in region>
	public Map<String, Integer> categorizeByRegion(List<User> user) {
		Map<String, Integer> regMap = new TreeMap<>();
		for(User u: user) {
			if(regMap.containsKey(u.getRegion())) {
				Integer count = regMap.get(u.getRegion());
				regMap.put(u.getRegion(), count + 1);
			} else {
				regMap.put(u.getRegion(),1);
			}
		}
		return regMap;
	}
	
	public Map<String, Object> processUserSpendAndPayHistrories(User user){
			
			//getting total spent amount
			List<List<Spend>> spendHistoryTemp = user.getCreditCards().stream()
					.map((cc) -> cc.getSpendHistory())
					.collect(Collectors.toList());
			
			List<Spend> spendHistory = null;
			Double totalSpent = 0d;
			
			Integer spendRecordCount = 0;
			for(List<Spend> spendList: spendHistoryTemp) {
				for(Spend spend: spendList) {
					totalSpent += spend.getAmount();
					spendRecordCount ++;
				}
			}
			Double avgSpent = totalSpent/spendRecordCount;
			
			//getting total payment amount
			List<List<Payment>> paymentHistoryTemp = user.getCreditCards().stream()
					.map((cc) -> cc.getPaymentHistory())
					.collect(Collectors.toList());
			Double totalPayed = 0d;
			for(List<Payment> paymentList: paymentHistoryTemp) {
				for(Payment payment: paymentList) {
					totalPayed += payment.getAmount();
				}
			}
			Double owed = totalSpent - totalPayed;
	
			String classification;
	
			if(avgSpent.compareTo(50d) < 0) {
				classification = "low spender";
			}else if(avgSpent.compareTo(150d) < 0) {
				classification = "medium spender";
			}else if(avgSpent.compareTo(300d) < 0) {
				classification = "high spender";
			}else {
				classification = "very high spender";
			}
			
			DecimalFormat df = new DecimalFormat(".##");
			Map<String, Object> classificationMap = new HashMap<>();
			classificationMap.put("total spent", df.format(totalSpent));
			classificationMap.put("total payed", df.format(totalPayed));
			classificationMap.put("amount owed", df.format(owed));
			classificationMap.put("average purchase", df.format(avgSpent));
			classificationMap.put("classification", classification);
			return classificationMap;
	}

}
