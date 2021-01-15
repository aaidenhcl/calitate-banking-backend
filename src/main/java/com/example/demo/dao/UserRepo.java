package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.service.Demographics;


@Component
public interface UserRepo extends JpaRepository<User, Long>{

	List<User> findByUsername(String username);
	
	//Query that retrieves user profession demographics
	@Query("SELECT new com.example.demo.service.Demographics(u.profession AS title, COUNT(u.profession) AS total) FROM User u GROUP BY title ORDER BY title ASC")
	public List<Demographics> getDemographicsProfession();
	
	//Query that receives user region demographics
	@Query("SELECT new com.example.demo.service.Demographics(u.region AS region, COUNT(u.region) AS total) FROM User u GROUP BY region ORDER BY region ASC")
	public List<Demographics> getDemographicsRegion();
	
	//Query that retrieves ages of users
	@Query(value="SELECT * FROM User WHERE DATEDIFF(YEAR,DOB,CURRENT_TIMESTAMP) BETWEEN ?1 AND ?2",nativeQuery = true)
	public List<User> getDemographicsAge(Integer lowerAge, Integer UpperAge);
	
	
	/*old query to select all data from user
	@Query("SELECT u FROM User u")
	public List<User> getAllUsers();
	*/
	
}
