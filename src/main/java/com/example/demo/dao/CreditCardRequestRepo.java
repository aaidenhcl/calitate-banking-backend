package com.example.demo.dao;

import org.springframework.stereotype.Component;

import com.example.demo.model.CreditCardRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Component
public interface CreditCardRequestRepo extends JpaRepository<CreditCardRequest, Long>{
	
	
	//test
	@Query(value = "select * from credit_card_request;" , nativeQuery = true)
	public List<CreditCardRequest> findAllStatus();
	
	@Query(value = "select * from credit_card_request;", nativeQuery = true)
	public List<CreditCardRequest> getStatusList();
}
 