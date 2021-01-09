package com.example.demo.dataseed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AccountRepo;
import com.example.demo.dao.UserRepo;
import com.example.demo.model.Account;
import com.example.demo.model.User;

@Component
public class DataLoader implements CommandLineRunner{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public void run(String... args) throws Exception {
		loadUserData();
		loadAccountData();
	}

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
						790, "FBI", "veryGoodPassword");
				userRepo.save(user3);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
		}
	}
	
	public void loadAccountData(){
		System.out.println("ACCOUNT SEED HIT");
		if(accountRepo.count() < 1) {
			System.out.println("ACCOUNT SEED CONDITIONAL PASSED");
			Long id1 = 1l;
			
			try {				
				Optional<User> userOpt = userRepo.findById(id1);
				if(userOpt != null) {
					User user1 = userOpt.get();
					Account account1 = new Account(5000d, 0.1d, 30000d,
							0.2d, "019273001", user1);
					accountRepo.save(account1);
				}
			}catch (Exception e) {
				System.err.println("Issue in loadAccountData in seed. ERROR::: " + e);
			}
		}
	}

}
