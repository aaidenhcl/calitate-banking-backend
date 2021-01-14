package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.Spend;

import com.example.demo.service.RegionSpend;
import com.example.demo.service.Region;

@Component
public interface SpendRepo extends JpaRepository<Spend, Long> {
	
	/*
	@Query(value = "SELECT u.region, COUNT(s.amount) FROM User U, Credit_Card c, Spend S WHERE u.id = c.user_id AND c.id = s.credit_card_id GROUP BY u.region ORDER BY u.region ASC", nativeQuery=true)
    public List<RegionSpend> getJoinInformation();
  	*/
	
	//return spends for all regions
	@Query("SELECT new com.example.demo.service.Region(u.region, SUM(s.amount) AS summed, COUNT(u.region) AS sale) FROM User u JOIN u.creditCards c JOIN c.spendHistory s GROUP BY u.region ORDER BY summed ASC")
    public List<Region> getRegionStats();
	
	
}
