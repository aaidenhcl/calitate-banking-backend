package com.example.demo.dao;

import com.example.demo.model.CreditCard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardRepo extends JpaRepository<CreditCard, Long>{

}
