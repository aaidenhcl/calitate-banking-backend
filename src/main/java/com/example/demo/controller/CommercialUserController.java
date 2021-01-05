package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.dao.CommercialUserRepo;

@RestController
public class CommercialUserController {

	@Autowired
	CommercialUserRepo repo;
//	
//	@GetMapping("/getUser/{username}")
//	public User getUser(@PathVariable("username") String username) {
//		User foundUser = null;
//		
//		try {
//			foundUser = repo.findByUsername(username).get(0);			
//		}catch (Exception e){
//			System.out.println("No user found with that username.");
//			System.err.println("ERROR: " + e);
//		}
//		return foundUser;
//	}
	
}
