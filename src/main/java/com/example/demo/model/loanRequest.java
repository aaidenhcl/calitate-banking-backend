package com.example.demo.model;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class loanRequest {

		@Id
		@GeneratedValue
		private long id;
		private String status;
		private Double offeredAmount;
		private float offeredApr;
		private Integer termInMonths;
		private Double minimumMonthlyPayment;
		
		//many loanRequest has one user
		@ManyToOne
		@JoinColumn(name="user_id")
		public User user;
}
