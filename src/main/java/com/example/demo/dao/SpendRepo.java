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
	

	
	//return spends and sales for all regions
	@Query("SELECT new com.example.demo.service.Region(u.region, SUM(s.amount) AS summed, COUNT(u.region) AS sale) FROM User u JOIN u.creditCards c JOIN c.spendHistory s GROUP BY u.region ORDER BY summed ASC")
    public List<Region> getRegionStats();
	

//	@Query("FROM spend where credit_card_id = ?1")
//    public List<Spend> getAllStatement(Long credit_card_id);

	
}
