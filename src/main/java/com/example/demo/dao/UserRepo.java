package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public interface UserRepo extends JpaRepository<User, Long>{

	List<User> findByUsername(String username);
	
}
