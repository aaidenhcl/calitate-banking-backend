package com.example.demo.dao;

import org.springframework.stereotype.Component;

import com.example.demo.model.CreditCardRequest;

import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface CreditCardRequestRepo extends JpaRepository<CreditCardRequest, Long>{

}
 