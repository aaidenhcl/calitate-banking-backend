package com.example.demo.model;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class creditCardRequest {

	@Id
	@GeneratedValue
	private Long id;
	private String status;
	private Double offeredLimit;
	private Double offeredApr;
	
	//many creditCardRequsts has one user
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;
}
