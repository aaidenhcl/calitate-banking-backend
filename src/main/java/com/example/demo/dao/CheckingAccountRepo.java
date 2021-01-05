package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CheckingAccount;

public interface CheckingAccountRepo extends JpaRepository<CheckingAccount, Long>{

	
}
