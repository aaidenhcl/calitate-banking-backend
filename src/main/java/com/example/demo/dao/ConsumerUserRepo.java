package com.example.demo.dao;

import com.example.demo.model.ConsumerUser;
import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerUserRepo extends JpaRepository<ConsumerUser, Long>{

	List<ConsumerUser> findByUsername(String username);
	
}
