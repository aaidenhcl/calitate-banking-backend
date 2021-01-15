package com.example.demo.dao;

import com.example.demo.model.CreditCard;
import com.example.demo.model.Spend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardRepo extends JpaRepository<CreditCard, Long>{
	
	public CreditCard findByCreditCardNumber(String creditCardNumber);
	
}
