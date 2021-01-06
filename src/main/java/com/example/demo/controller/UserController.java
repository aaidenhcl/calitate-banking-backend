package com.example.demo.controller;

import com.example.demo.dao.UserRepo;
//import com.example.demo.model.ConsumerUser;
import com.example.demo.model.User;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	UserRepo repo;
	
	/*
	 * On save, this route is going to hit the User default method.
	 * Uses setter based injection to set username and password.
	 * Does not save the password. Check setPassword in User.
	 * Rather, hits hashedPassword method which saves
	 * a hash and salt to the instance.
	 */
	@PostMapping(path="/user")
	public User createUser(@RequestBody User user){
		System.out.println(user);
		repo.save(user);
		return user;
	}
	
	/*
	 * This route is a little messy at the moment and could
	 * use some refactoring.
	 * Hits authenticateLogin and RETURNS a JWT token if credentials 
	 * are valid. returns null if invalid credentials.
	 */
	@GetMapping(path="/login/{username}")
	public String loginUser(@PathVariable("username") String username, @RequestHeader(value="password") String password) {
		User foundUser = null;

		//this is a bit messy but it parses JSON down to the
		//bare naked password
		password = password.replaceAll("\\{", "");
		password = password.replaceAll("\\}", "");
		password = password.replaceAll("\"", "");
		//for debugging
		System.out.println("REQUEST PASSWORD::: " + password);
		
		//saves password into a map as {"password": "<usersPassword>"}
//		Map<String, String> reconstructedUtilMap = Arrays.stream(password.trim().split(","))
//				.map(s -> s.split(":"))
//				.collect(Collectors.toMap(s -> s[0], s-> s[1]));
		
		//for debugging
		System.out.println("KEY:::");
		//used to gather "password" key for value access later
//		Object[] keySet = reconstructedUtilMap.keySet().toArray();
		
		//for debugging
//		reconstructedUtilMap.keySet().forEach(System.out::println);
//		System.out.println(reconstructedUtilMap.get(keySet[0]));
				
		String token = null;
		//finds user by username and auithenticates the user's credentials
		try {
			//retrieves User from database
			foundUser = repo.findByUsername(username).get(0);	
			//for debugging
			System.out.println("USER FOUND: " + foundUser);
			
			//VERY IMPORTANT authenticates the user
			//returns token if user auth successful
			if(foundUser != null) {
				token = foundUser.authenticateLogin(password, foundUser);
				if(token != null) {
					return token;
				}
			} else {
				//if credentials incorrect
				System.out.println("LOGIN FAILED");
				return null;
			} 
		}catch (Exception e){
			System.out.println("No user found with that username.");
			System.err.println("ERROR: " + e);
		}
			return token;
		}
	
		/*
		 * This route takes a token as a header and validates that token.
		 * Token is checked for validity as well as expiration.
		 * If token is valid then user is retreived from database
		 * and returned to client.
		 */
		@GetMapping(path="/obtainUserData/{username}")
		public User obtainUserData(@RequestHeader(value="Authorization") String token, @PathVariable("username") String username) {
			User foundUser = null;
			if(User.validateUserToken(token)) {
				try {					
					foundUser = repo.findByUsername(username).get(0);
					return foundUser;
				}catch (Exception e) {
					System.err.println("Error in obtainUserData" + e);
				}
			}
				return null;
		}
	
}