package com.example.demo.dao;

import com.example.demo.model.CommercialUser;
import com.example.demo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommercialUserRepo extends JpaRepository<CommercialUser, Long>{

//	List<User> findByUsername(String username);
	
}
