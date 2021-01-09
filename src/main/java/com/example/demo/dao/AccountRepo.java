package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;
import com.example.demo.model.User;

@Component
public interface AccountRepo extends JpaRepository<Account, Long>{
	
}
