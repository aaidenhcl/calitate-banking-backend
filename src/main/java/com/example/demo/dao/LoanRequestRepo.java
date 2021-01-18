package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.LoanRequest;

public interface LoanRequestRepo extends JpaRepository<LoanRequest, Long>{

	List<LoanRequest> findByUserRegion(String region);
	List<LoanRequest> findByUserProfession(String profession);
	List<LoanRequest> findByUserRegionAndUserProfession(String region, String profession);
	
	@Query(value = "select * from loan_request;", nativeQuery = true)
	public List<LoanRequest> getStatusList();
	
	@Query(value = "select * from laon_request;" , nativeQuery = true)
	public List<LoanRequest> findAllStatus();
	
	public List<LoanRequest> findAllByStatus(String status);
}
