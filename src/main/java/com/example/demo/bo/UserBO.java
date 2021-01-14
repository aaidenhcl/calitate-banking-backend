package com.example.demo.bo;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.CreditCard;
import com.example.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

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
}
