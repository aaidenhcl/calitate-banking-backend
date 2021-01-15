package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.Demographics;
import com.example.demo.service.RegionSale;

@Component
public interface UserRepo extends JpaRepository<User, Long>{

	List<User> findByUsername(String username);
	
	//Need to find way to use category as a parameter
	@Query("SELECT new com.example.demo.service.Demographics(u.profession AS title, COUNT(u.profession) AS total) FROM User u GROUP BY title ORDER BY title ASC")
	public List<Demographics> getDemographics();
	
	
	@Query("SELECT new com.example.demo.service.RegionSale(u.region, COUNT(c.id) AS sale) FROM User u JOIN u.creditCards c GROUP BY u.region ORDER BY u.region ASC")
	public List<RegionSale> getRegionSale();
	
	
}
