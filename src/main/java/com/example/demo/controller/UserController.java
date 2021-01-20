package com.example.demo.controller;

import com.example.demo.bo.UserBO;
import com.example.demo.dao.UserRepo;
import com.example.demo.exceptions.CorruptDatabaseException;
import com.example.demo.exceptions.NotAuthorizedException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.Payment;
import com.example.demo.model.Spend;
import com.example.demo.model.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.demo.service.AgeDemographics;
import com.example.demo.service.DemographicsProfession;
import com.example.demo.service.DemographicsRegion;
import com.example.demo.utilities.DevUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
	
	@GetMapping(path="/users")
	public String getAllUsers(@RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
		if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			
			Map<Long, String> mappedUsers = new HashMap<>();
			List<User> users = null;
			try{
//				System.out.println("HELLOOOOO");
				users = bo.findAll();
//				System.out.println(users.toString());
				
				for(User user: users) {
					mappedUsers.put(user.getId(), user.toString());
				}
				
				return gson.toJson(mappedUsers);
//				return mappedUsers;
			}catch (Exception e) {
				System.err.println("PROBLEM FOUND"+e);
			}
			return null;
		}
		throw new NotAuthorizedException("User is not authorized");
	}

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


	/*
	 * This method accepts the secret code from the user
	 * and returns an auth token if the code is correct.
	 */
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

		/*
		 * This route obtains the total credit limit across all of a
		 * users Credit Cards and returns them to the client
		 */
		@GetMapping(path="/users/{username}/totalCredit")
		public Map<String, String> obtainUserTotalCredit(@RequestHeader(value="Authorization") String token, @PathVariable("username") String username) throws NotAuthorizedException, CorruptDatabaseException {
			
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {														
				try {
					Map<String, String> toReturn = new LinkedHashMap<>();
					List<CreditCard> ccs = bo.getAllCreditCardsByUsername(username);
					Double totalLimit = 0.0;
					for (CreditCard cc : ccs) {
						totalLimit += cc.getSpendingLimit();
					}
					toReturn.put("The total credit limit for user " + username,"$"+String.format("%.2f", totalLimit));
					for (CreditCard cc : ccs) {
						String entry = "Limit for card ending in ";
						if (cc.getCreditCardNumber() != null)
							entry+=cc.getCreditCardNumber().substring(12);
						
						toReturn.put(entry+": ", String.format("$%.2f", cc.getSpendingLimit()));
					}
					return toReturn;
				}
				catch(Exception e) {
					if(e instanceof NullPointerException) {
						throw new CorruptDatabaseException("values for credit card status and/or spending_limit cannot be null. check your records");
					}
					System.err.println("Error in totalCredit::: " + e);
				}
				return null;
			} 
			throw new NotAuthorizedException("User is not authorized");
		}
		
		
		/*
		 * This route supplies the client with a user's payment history,
		 * including whether a sufficient monthly payment has been made.
		 */
		@SuppressWarnings("deprecation")
		@GetMapping(path="/users/{username}/paymentHistory")
		public Map<String, Map<String, Map<String, Map<String, Map<String, String>>>>> paymentLimits(@PathVariable("username") String username){
			
			// old code
			/*User u = bo.findByUsername(username);
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
			}*/
			
			User u = bo.findByUsername(username);
			Map<String,Map<String, Map<String, Map<String,Map<String,String>>>>> toReturn = new LinkedHashMap<>();
			for (CreditCard cc : u.getCreditCards()) {
				//System.out.println(cc.getDateCreated());
				if (cc.getDateCreated() == null)
					continue;
				
				LinkedHashMap<String, ArrayList<Double>> payments = new LinkedHashMap<>();
				Date current = (Date) cc.getDateCreated().clone();
				while(current.before(new Date())) {
					payments.put(current.getYear()+""+current.getMonth(), new ArrayList<>());
					current.setMonth(current.getMonth()+1);
					//System.out.println(current);
				}
				for (Payment p : cc.getPaymentHistory()) {
					payments.get(p.getDateCreated().getYear()+""+p.getDateCreated().getMonth()).add(p.getAmount());
				}
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				//System.out.println(gson.toJson(payments));
				
				
				LinkedHashMap<String, ArrayList<Double>> spends = new LinkedHashMap<>();
				current = cc.getDateCreated();
				while(current.before(new Date())) {
					spends.put(current.getYear()+""+current.getMonth(), new ArrayList<>());
					current.setMonth(current.getMonth()+1);
					//System.out.println(current);
				}
				//System.out.println(gson.toJson(spends));
				for (Spend s : cc.getSpendHistory()) {
					//System.out.println(s.getDateCreated().getYear()+""+s.getDateCreated().getMonth());
					spends.get(s.getDateCreated().getYear()+""+s.getDateCreated().getMonth()).add(s.getAmount());
				}
				//System.out.println(gson.toJson(spends));
				
				
				
				Map<String,Map<String, Map<String, Map<String, String>>>> month = new LinkedHashMap<>();
				double balance = 0.0;
				for (String i : spends.keySet()) {
					Map<String, String> spendsPayments = new LinkedHashMap<>();
					Map<String, Map<String, String>> monthlyPayment = new LinkedHashMap<>();
					double totalSpends = 0.0, totalPayments = 0.0;
					for (double s : spends.get(i))
						totalSpends += s;
					for (double p : payments.get(i))
						totalPayments += p;
					balance += totalSpends;
					
					spendsPayments.put("Total Spends: "+totalSpends, "Total Payments: "+totalPayments);
					
					if (totalPayments > (.1 * balance) || balance == 0.0) {
						monthlyPayment.put("Met Monthly Minimum Payment - TRUE",spendsPayments);
						//System.out.println("true "+i);
					}
					else {
						//System.out.println("false "+i);
						monthlyPayment.put("Met Monthly Minimum Payment - FALSE",spendsPayments);
					}
					String yearS = i.substring(0,3);
					String monthS = i.substring(3);
					int yearNumber = 1900 + Integer.parseInt(yearS);
					int monthNumber = 1+Integer.parseInt(monthS);
					Map<String, Map<String, Map<String, String>>> balanceMap = new LinkedHashMap<>();
					balanceMap.put("Balance before payments at end of month: "+balance, monthlyPayment);
					month.put("Year: "+yearNumber+" - Month: "+monthNumber, balanceMap);
					balance -= totalPayments;
				}
				toReturn.put("For card ending in "+cc.getCreditCardNumber().substring(12), month);
			}
			
			return toReturn; 
		}
		
		//method returns sale based on region

		
		//method returns count of users based on profession
		@GetMapping(path="users/demographics/profession")
		public List<DemographicsProfession> getDemographicsProfession(@RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {
				List<DemographicsProfession> dl = bo.getDemographicsProfession();
				return dl;
			}
			throw new NotAuthorizedException("User is not authorized");
		}
		
		
		//method returns count of users based on region
		@GetMapping(path="users/demographics/region")
		public List<DemographicsRegion> getDemographicsRegion(@RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)){
				List<DemographicsRegion> rl = bo.getDemographicsRegion();
				return rl;
			}
			throw new NotAuthorizedException("User is not authorized");
		}
		
		//method returns count of ages of users
		@GetMapping(path="users/demographics/age")
		public List<AgeDemographics> getDemographicsAge(@RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)){
				List<AgeDemographics> adl = new ArrayList<AgeDemographics>();
				
				for(Integer i = 18; i < 100;i+=10) {
					List<User> ul = bo.getDemographicsAge(i,i+10);
					if(i==18) {
						adl.add(new AgeDemographics((i + " - " + (i+12)),(Integer)ul.size()));
						i=20;
					}
					adl.add(new AgeDemographics((i + " - " + (i+10)),(Integer)ul.size()));
				}
				return adl;
			}	
			throw new NotAuthorizedException("User is not authorized");
		}
	
		/*
		 * This route gets a user's spend and payment histories
		 * find accumulated total of both
		 * returns classification depending on amount owed and credit score
		 *
		 */
		@GetMapping(path="/users/{username}/classification")
		public String userTransactionStats(@PathVariable("username") String username, @RequestHeader(value="Authorization") String token) throws NotAuthorizedException{
			if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
				User user = bo.findByUsername(username);
				Map<String, Object> mappedAmounts = bo.processUserSpendAndPayHistrories(user);
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.create();

				return gson.toJson(mappedAmounts);
			}
			throw new NotAuthorizedException("User is not authorized");
		}

		
//		@GetMapping(path="/users/{id}/clasification")
//		public String userClassification(@PathVariable("id") Long id, @RequestHeader(value="Authorization") String token) {
//			if(DevUtil.getIsDev() || User.validateUserToken(token)) {						
//				
//			}
//			return null;
//		}
		
		/*
		 * This route gets a user's spend and payment histories
		 * calculates average spent per month
		 * calculates average payments per month
		 * returns average difference
		 * This is a bit much and probably wont do.
		 * Just an idea really
		 */

}
