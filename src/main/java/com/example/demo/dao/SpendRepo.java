package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.Spend;

import com.example.demo.service.RegionSpend;

@Component
public interface SpendRepo extends JpaRepository<Spend, Long> {

	//Querys that retrieves spends/sales based on user regions
	@Query("SELECT new com.example.demo.service.RegionSpend(u.region, SUM(s.amount) AS summed) FROM User u JOIN u.creditCards c JOIN c.spendHistory s GROUP BY u.region ORDER BY summed DESC")
    public List<RegionSpend> getRegionSpend();

}
