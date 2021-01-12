package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Spend;

@Component
public interface SpendRepo extends JpaRepository<Spend, Long> {
	
}
