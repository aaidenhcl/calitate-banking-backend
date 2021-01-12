package com.example.demo.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AccountRepo;
import com.example.demo.model.Account;
import com.example.demo.model.Spend;
import com.example.demo.dao.SpendRepo;

@RestController
public class SpendController {

		@Autowired
		SpendRepo repo;
		
		//Get route
		@GetMapping(path="/spends/{id}")
		public Spend getSpend(@PathVariable("id") Long id){
			
			Optional<Spend> spendOpt = repo.findById(id);
			
			//Test if id is not null
			if (spendOpt == null) {
				return null;
			}
			
			//Type cast a new spend
			Spend spend = spendOpt.get();
			
			
			return spend;
		}
		
		//Post Route
		@PostMapping(path = "/spends")
		public void create( Spend newSpend) {
			
			//Testing
			System.out.println(newSpend);
			
			//Save 
			repo.save(newSpend);
			
		}
}

