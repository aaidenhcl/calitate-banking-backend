package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.DemographicsProfession;
import com.example.demo.service.DemographicsRegion;


@Component
public interface UserRepo extends JpaRepository<User, Long>{

	List<User> findByUsername(String username);
	
	//Query that retrieves user profession demographics
	@Query("SELECT new com.example.demo.service.DemographicsProfession(u.profession AS title, COUNT(u.profession) AS total) FROM User u GROUP BY title ORDER BY total DESC")
	public List<DemographicsProfession> getDemographicsProfession();
	
	//Query that receives user region demographics
	@Query("SELECT new com.example.demo.service.DemographicsRegion(u.region AS region, COUNT(u.region) AS total) FROM User u GROUP BY region ORDER BY total DESC")
	public List<DemographicsRegion> getDemographicsRegion();
	
	//Query that retrieves ages of users based on given parameters
	@Query(value="SELECT * FROM User WHERE DATEDIFF(YEAR,DOB,CURRENT_TIMESTAMP) BETWEEN ?1 AND ?2",nativeQuery = true)
	public List<User> getDemographicsAge(Integer lowerAge, Integer UpperAge);
	
}
