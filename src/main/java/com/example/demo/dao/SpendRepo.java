package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.example.demo.model.Spend;

@Component
public interface SpendRepo extends JpaRepository<Spend, Long> {
	
//	@Query("FROM spend where credit_card_id = ?1")
//    public List<Spend> getAllStatement(Long credit_card_id);
	
}
