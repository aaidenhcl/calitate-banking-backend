package com.example.demo.dataseed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AccountRepo;
import com.example.demo.dao.CreditCardRepo;
import com.example.demo.dao.CreditCardRequestRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.Account;
import com.example.demo.model.CreditCard;
import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;

/*
 * This file will be responsible for seeding the database
 * for testing and demo purposes.
 */
@Component
public class DataLoader implements CommandLineRunner{
	
	/*
	 * Autowiring repos for data persistence.
	 * Autowires are in order of seeding.
	 */
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private CreditCardRequestRepo creditCardRequestRepo;
	
	@Autowired
	private CreditCardRepo creditCardRepo;

	/*
	 * Main starting method that calls all 
	 * preseeding(lowl) methods. Order is important
	 */
	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadAccountData();
		loadCreditCardRequestData();
		loadCreditCardData();
	}

	/*
	 * Loads users for testing
	 */
	private void loadUserData() {
		if(userRepo.count() == 0) {
			
			//SETTING USER 1
			Date date1 = null;
			try {
				//conveniently sets the date :) 
				date1 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user1 = new User("jeremy_beremy", date1, "Jeremy", "Matthew", "101 Rock and Hardplace drive, 78321", "Michigan",
						650, "cobbler", "verySecretPassword");
				userRepo.save(user1);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
			//SETTING USER 2
			Date date2 = null;
			try {
				date2 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user2 = new User("samiylo123", date2, "Samiylo", "Kryshu", "Old San Antonio Dr, 78249", "Texas",
						530, "cowboy", "verySecretPassword");
				userRepo.save(user2);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
			//SETTING USER 3
			Date date3 = null;
			try {
				date3 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user3 = new User("mattInDaHawse", date3, "Matthew", "Krueger", "Chicken Biscuit Rd., 21341", "Texas",
						790, "FBI", "veryGoodPassword", "kruegermatthewr@gmail.com");
				userRepo.save(user3);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
		}
	}
	
	/*
	 * Loads accounts for testing
	 */
	public void loadAccountData(){
		System.out.println("ACCOUNT SEED HIT");
		if(accountRepo.count() < 1) {
			System.out.println("ACCOUNT SEED CONDITIONAL PASSED");			
			try {				
				Long id1 = 1l;
				Optional<User> userOpt1 = userRepo.findById(id1);
				if(userOpt1 != null) {
					User user1 = userOpt1.get();
					Account account1 = new Account(5000d, 0.1d, 30000d,
							0.2d, "019273001", user1);
					accountRepo.save(account1);
				}
				
				Long id2 = 2l;
				Optional<User> userOpt2 = userRepo.findById(id2);
				if(userOpt2 != null) {
					User user2 = userOpt2.get();
					Account account2 = new Account(1000d, 0.1d, 3000d,
							0.2d, "019629001", user2);
					accountRepo.save(account2);
				}
				
				Long id3 = 3l;
				Optional<User> userOpt3 = userRepo.findById(id3);
				if(userOpt3 != null) {
					User user3 = userOpt3.get();
					Account account3 = new Account(8000d, 0.3d, 100000d,
							0.5d, "024629001", user3);
					accountRepo.save(account3);
				}
				
			}catch (Exception e) {
				System.err.println("Issue in loadAccountData in seed. ERROR::: " + e);
			}
		}
	}
	
	/*
	 * Seeds credit card request data
	 */
	public void loadCreditCardRequestData() {
		
		try{
			//Credit card request 1 data seeded
			//all of user 2's ccr's
			Long id1 = 1l;
			Long id2 = 2l;
			Long id3 = 3l;
			
			Optional<User> userOpt1 = userRepo.findById(id1);
			Optional<User> userOpt2 = userRepo.findById(id2);
			Optional<User> userOpt3 = userRepo.findById(id3);

			if(userOpt1 != null || userOpt2 != null || userOpt3 != null  ) {		
				User user1 = userOpt1.get();
				User user2 = userOpt2.get();
				User user3 = userOpt3.get();
				
				//user 1's requests...
				//should save status as refused with reason
				CreditCardRequest ccr1 = new CreditCardRequest("platinum", user1);
				creditCardRequestRepo.save(ccr1);

				//should save status as approved
				CreditCardRequest ccr2 = new CreditCardRequest("gold", user1);
				creditCardRequestRepo.save(ccr2);
				
				//should save status as approved
				CreditCardRequest ccr3 = new CreditCardRequest("silver", user1);
				creditCardRequestRepo.save(ccr3);
				
				//should save status as refused with reason
				CreditCardRequest ccr4 = new CreditCardRequest("gold", user2);
				creditCardRequestRepo.save(ccr4);
				
				//should save status as approved
				CreditCardRequest ccr5 = new CreditCardRequest("silver", user2);
				creditCardRequestRepo.save(ccr5);
				
				//should save status as approved
				CreditCardRequest ccr6 = new CreditCardRequest("platinum", user3);
				creditCardRequestRepo.save(ccr6);
				
				//should save status as approved
				CreditCardRequest ccr7 = new CreditCardRequest("gold", user3);
				creditCardRequestRepo.save(ccr7);
				
				
				//users accepting/declining the credit card requests
				try {			
//					System.out.println(user1);
//					CreditCard cred1 = user1.getCreditCardRequests().get(1).acceptOffer();
					CreditCard cred1 = ccr2.acceptOffer();
					creditCardRepo.save(cred1);
					
//					user1.getCreditCardRequests().get(1).declineOffer();
//					
//					System.out.println(user2);
//					CreditCard cred2 = user2.getCreditCardRequests().get(1).acceptOffer();
//					creditCardRepo.save(cred2);
//
//					System.out.println(user3);
//					CreditCard cred3 = user3.getCreditCardRequests().get(0).acceptOffer();
//					creditCardRepo.save(cred3);
				}catch (Exception e) {
					System.err.println("ERROR IN CreditCard loader:: " + e);
				}
	
			}
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void loadCreditCardData() {
		
	}

}
