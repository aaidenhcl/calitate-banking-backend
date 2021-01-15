package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Spend;
import com.example.demo.service.RegionSpend;

import com.example.demo.bo.SpendBO;
import com.example.demo.dao.AccountRepo;
import com.example.demo.model.Account;
import com.example.demo.model.Spend;
import com.example.demo.model.User;
import com.example.demo.utilities.DevUtil;
import com.example.demo.dao.SpendRepo;



import com.example.demo.dao.SpendRepo;
@RestController
public class SpendController {

		@Autowired
		SpendRepo repo;
		

		@Autowired
		SpendBO bo;

		//Index route / get All
		@GetMapping(path= "/spends")
		public List<Spend> getSpends(@RequestHeader("Authorization") String token) {
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {								
				List<Spend> list = repo.findAll();
				
				return list;				
			}
			return null;
		}
		
		//Get route / Find
		@GetMapping(path="/spends/{id}")
		public Spend getSpend(@PathVariable("id") Long id, @RequestHeader("Authorization") String token){
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {												
				Optional<Spend> spendOpt = repo.findById(id);
				
				//Test if id is not null
				if (spendOpt == null) {
					return null;
				}
				
				//Type cast a new spend
				Spend spend = spendOpt.get();
				
				
				return spend;
			}
			return null;
		}
		
		
		//Get spending based on region
		@GetMapping(path="spends/regionSpend")
		public List<RegionSpend> getRegionSpend(){
			List<RegionSpend> rs = repo.getRegionSpend();
			return rs;
		}
		
		
		//Post Route / Save / Update
		//Confirmed functionality
		@PostMapping(path = "/spends")
		public void create(@RequestBody Spend newSpend, @RequestHeader("Authorization") String token) {
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {												
				//Testing
				System.out.println(newSpend);
				
				//Save 
				repo.save(newSpend);
				
			}
		}
		
		
		//Delete Route
		@DeleteMapping(path= "/spends/{id}")
		public void delete(@PathVariable("id") Spend spend, @RequestHeader("Authorization") String token) {
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {												
				//Testing
				System.out.println("In Spends" + spend);
				
				//Delete spend from repo
				repo.delete(spend);
			}
		}
}

