package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		@GetMapping(path="/spends/:id/edit")
		public Spend findById(@RequestBody Spend spend){
			System.out.println("In function");
			
			spend = (Spend) repo.findById(spend);
			
			return spend;
		}
}

