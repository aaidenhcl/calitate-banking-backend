package com.example.demo.dao;

import org.springframework.stereotype.Component;

import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;
import com.example.demo.service.CreditCardDiscontinued;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Component
public interface CreditCardRequestRepo extends JpaRepository<CreditCardRequest, Long>{

	List<CreditCardRequest> findByUserRegion(String region);
	List<CreditCardRequest> findByUserProfession(String profession);
	List<CreditCardRequest> findByUserRegionAndUserProfession(String region, String profession);
	
	@Query(value = "SELECT * FROM credit_card_request WHERE request_time BETWEEN CAST(?1 AS DATE) AND CAST(?2 AS DATE);", nativeQuery = true)
	List<CreditCardRequest> findByRequestTime(String start, String end);
	
	//test
	@Query(value = "select * from credit_card_request;" , nativeQuery = true)
	public List<CreditCardRequest> findAllStatus();
	
	@Query(value = "select * from credit_card_request;", nativeQuery = true)
	public List<CreditCardRequest> getStatusList();
	
	public List<CreditCardRequest> findAllByStatus(String status);
	
//	@Query(value = "SELECT FROM credit_card_request ccr WHERE ccr.status = 'rejected'", nativeQuery = true)
//	public List<CreditCardRequestRejectedReasonCount> findAllCountRejectedAndReason();
	
}
 