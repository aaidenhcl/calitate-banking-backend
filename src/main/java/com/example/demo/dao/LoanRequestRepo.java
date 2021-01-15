package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LoanRequest;

public interface LoanRequestRepo extends JpaRepository<LoanRequest, Long>{

}
