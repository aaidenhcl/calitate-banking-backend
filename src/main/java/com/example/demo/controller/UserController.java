package com.example.demo.controller;

import com.example.demo.bo.UserBO;
import com.example.demo.dao.UserRepo;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.demo.service.Demographics;
import com.example.demo.service.RegionSale;
import com.example.demo.service.RegionSpend;

import com.example.demo.utilities.DevUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	UserRepo repo;

	@Autowired
	UserBO bo;

	/*
	 * On save, this route is going to hit the User default method.
	 * Uses setter based injection to set username and password.
	 * Does not save the password. Check setPassword in User.
	 * Rather, hits hashedPassword method which saves
	 * a hash and salt to the instance.
	 */
	@PostMapping(path="/users")//changed to users from user. update frontend
	public User createUser(@RequestBody User user){
		System.out.println(user);
		repo.save(user);
		return user;
	}//user logger rather than sysout

	/*
	 * This route is a little messy at the moment and could
	 * use some refactoring.
	 * Hits authenticateLogin and RETURNS a JWT token if credentials
	 * are valid. returns null if invalid credentials.
	 */
	@GetMapping(path="users/login/{username}")//use post here rather than get
	public Boolean loginUser(@PathVariable("username") String username, @RequestHeader(value="password") String password) {
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

		//finds user by username and auithenticates the user's credentials
		try {
			//retrieves User from database
			foundUser = repo.findByUsername(username).get(0);
			//for debugging
			System.out.println("USER FOUND: " + foundUser);

			//VERY IMPORTANT authenticates the user
			//returns token if user auth successful
			if(foundUser != null) {
				if(foundUser.authenticateLogin(password, foundUser)){
					repo.save(foundUser);
					return true;
				}
			} else {
				//if credentials incorrect
				System.out.println("LOGIN FAILED");
				return false;
			}
		}catch (Exception e){
			System.out.println("No user found with that username.");
			System.err.println("ERROR: " + e);
		}
		return false;
		}


	@GetMapping(path="users/login/2fa/{username}")
	public String twoFactorAuth(@PathVariable("username") String username, @RequestHeader(value="twoFactorAuth") String code) {

		String token = null;
		User foundUser = repo.findByUsername(username).get(0);
		System.out.println("USER FOUND IN AUTH::: " + foundUser);
		try {

			if (foundUser.getTwoFactorAuth().equals(code)) {
				token = foundUser.authenticate2FA(foundUser);
				if (token != null) {
					foundUser.setTwoFactorAuth(null);
					return token;
				}
				else {
					System.out.println("LOGIN FAILED");
					foundUser.setTwoFactorAuth(null);
					return null;
				}
			}
		}
		catch (Exception e) {
			System.err.println("ERROR: " + e);
		}
		foundUser.setTwoFactorAuth(null);
		repo.save(foundUser);
		return token;

	}
		/*
		 * This route takes a token as a header and validates that token.
		 * Token is checked for validity as well as expiration.
		 * If token is valid then user is retreived from database
		 * and returned to client.
		 */
		@GetMapping(path="/users/{username}")//change obtainUserData to users
		public User obtainUserData(@RequestHeader(value="Authorization") String token, @PathVariable("username") String username) throws NotAuthorizedException{
			User foundUser = null;
			if(User.validateUserToken(token)) {
				try {
					foundUser = repo.findByUsername(username).get(0);
					return foundUser;
				}catch (Exception e) {
					System.err.println("Error in obtainUserData" + e);
				}
			}
			throw new NotAuthorizedException("User is not authorized");
		}

		@GetMapping(path="/users/{username}/totalCredit")
		public Double obtainUserTotalCredit(/*@RequestHeader(value="Authorization") String token, */@PathVariable("username") String username) {

			//if (User.validateUserToken(token)) {
				try {
					return bo.findTotalCreditLimitByUsername(username);
				}
				catch(Exception e) {
					System.err.println("Error in totalCredit");
				}
			//}
			return 0.0;
		}

		@GetMapping(path="/users/{username}/paymentHistory")
		public Map<String, Map<Double,Double>> paymentLimits(@PathVariable("username") String username){
			User u = bo.findByUsername(username);
			Map<String, Map<Double,Double>> toReturn = new LinkedHashMap<>();
			for (CreditCard cc : u.getCreditCards()) {
				String last4 = cc.getCreditCardNumber().substring(12);
				Double remainingBalance = cc.getBalance();
				LinkedHashMap<Double, Double> toAdd = new LinkedHashMap<>();
				for (Payment p : cc.getPaymentHistory()) {
					Double paymentAmount = p.getAmount();
					remainingBalance -= paymentAmount;
					toAdd.put(paymentAmount,cc.getSpendingLimit()-remainingBalance);
				}

				toReturn.put(last4,  toAdd);
			}
			return toReturn;
		}
		
		@GetMapping(path="users/regionSale")
		public List<RegionSale> getRegionSale(@RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {										
				List<RegionSale> rs = bo.getRegionSale();
				return rs;
			}
			throw new NotAuthorizedException("User is not authorized");
		}
		
		@GetMapping(path="users/demographics")
		public List<Demographics> getUserDemographics() {
			List<Demographics> dl = repo.getDemographics();
			return dl;

		}

		/*
		 * This route gets a user's spend and payment histories
		 * find accumulated total of both
		 * returns classification depending on amount owed and credit score
		 *
		 */
		@GetMapping(path="/users/{id}/classification")
		public String userTransactionStats(@PathVariable("id") Long id, @RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
				User user = bo.findById(id);
				Map<String, Object> mappedAmounts = bo.processUserSpendAndPayHistrories(user);
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				return gson.toJson(mappedAmounts);
			}
			throw new NotAuthorizedException("User is not authorized");
		}
		/*
		 * This route gets a user's spend and payment histories
		 * calculates average spent per month
		 * calculates average payments per month
		 * returns average difference
		 * This is a bit much and probably wont do.
		 * Just an idea really
		 */

}
