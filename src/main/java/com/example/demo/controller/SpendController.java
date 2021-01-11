package com.example.demo.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AccountRepo;
import com.example.demo.model.Account;
import com.example.demo.model.Spend;
import com.example.demo.dao.SpendRepo;

@RestController
public class SpendController {

		@Autowired
		SpendRepo repo;
		
		//Get mapping for editing a spend
		@GetMapping(path="/spends/{id}")
		public Spend getSpend(@PathVariable("id") Long id){
			
			Optional<Spend> spendOpt = repo.findById(id);
			
			if (spendOpt == null) {
				return null;
			}
			
			Spend spend = spendOpt.get();
			
			
			return spend;
		}
}

