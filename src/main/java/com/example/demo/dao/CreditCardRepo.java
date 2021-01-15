package com.example.demo.dao;

import com.example.demo.model.CreditCard;
import com.example.demo.service.RegionSale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardRepo extends JpaRepository<CreditCard, Long>{
	
	//Query that retrieves sales based on regions
	@Query("SELECT new com.example.demo.service.RegionSale(u.region, COUNT(c.id) AS sale) FROM User u JOIN u.creditCards c GROUP BY u.region ORDER BY u.region ASC")
	public List<RegionSale> getRegionSale();

	public CreditCard findByCreditCardNumber(String creditCardNumber);
	

}
