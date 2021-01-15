package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Loan;

public interface LoanRepo extends JpaRepository<Loan, Long>{

}
