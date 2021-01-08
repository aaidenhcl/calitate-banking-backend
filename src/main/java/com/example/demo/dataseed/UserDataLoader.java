package com.example.demo.dataseed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserRepo;
import com.example.demo.model.User;

@Component
public class UserDataLoader implements CommandLineRunner{

	@Autowired
	UserRepo repo;
	
	@Override
	public void run(String... args) throws Exception {
		loadUserData();
	}

	private void loadUserData() {
		if(repo.count() == 0) {
			
			//SETTING USER 1
			Date date1 = null;
			try {
				//conveniently sets the date :) 
				date1 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user1 = new User("jeremy_beremy", date1, "Jeremy", "Matthew", "101 Rock and Hardplace drive, 78321", "Michigan",
						650, "cobbler", "verySecretPassword");
				repo.save(user1);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
			//SETTING USER 2
			Date date2 = null;
			try {
				date2 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user2 = new User("samiylo123", date2, "Samiylo", "Kryshu", "Old San Antonio Dr, 78249", "Texas",
						530, "cowboy", "verySecretPassword");
				repo.save(user2);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
			//SETTING USER 3
			Date date3 = null;
			try {
				date3 = new SimpleDateFormat("MM/dd/yyyy").parse("11/07/1996");
				
				User user3 = new User("mattInDaHawse", date3, "Matthew", "Krueger", "Chicken Biscuit Rd., 21341", "Texas",
						790, "FBI", "veryGoodPassword");
				repo.save(user3);
			}catch (Exception e) {
				System.err.println("Issue creating user. ERROR: " + e);
			}
			
		}
	}
	
}
