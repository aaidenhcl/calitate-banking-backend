package com.example.demo.dao;

import com.example.demo.model.CreditCard;
import com.example.demo.service.CreditCardDiscontinued;
import com.example.demo.service.RegionSale;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardRepo extends JpaRepository<CreditCard, Long>{
	
	//Query that retrieves sales based on regions
	@Query("SELECT new com.example.demo.service.RegionSale(u.region, COUNT(c.id) AS sale) FROM User u JOIN u.creditCards c GROUP BY u.region ORDER BY sale DESC")
	public List<RegionSale> getRegionSale();
	
	//Query that retrieves discontinued credit cards based on User regions and professions
	@Query("SELECT new com.example.demo.service.CreditCardDiscontinued(COUNT(cc.status) AS ccd, u.region, u.profession) FROM User u JOIN u.creditCards cc WHERE cc.status = 'inactive' GROUP BY u.region, u.profession ORDER BY ccd DESC")
	public List<CreditCardDiscontinued> getDiscontinued();

	public CreditCard findByCreditCardNumber(String creditCardNumber);
	

}
