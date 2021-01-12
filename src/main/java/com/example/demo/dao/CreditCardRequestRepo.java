package com.example.demo.dao;

import org.springframework.stereotype.Component;

import com.example.demo.model.CreditCardRequest;
import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface CreditCardRequestRepo extends JpaRepository<CreditCardRequest, Long>{

	List<CreditCardRequest> findByUserRegion(String region);
	List<CreditCardRequest> findByUserProfession(String profession);
	List<CreditCardRequest> findByUserRegionAndUserProfession(String region, String profession);
	
}
 