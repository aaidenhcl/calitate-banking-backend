package com.example.demo.model;

import java.util.Date;

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
		private Date requestedTime;
		private Date updatedTime;
		private String reason;
		
		//many loanRequest has one user
		@ManyToOne
		@JoinColumn(name="user_id")
		public User user;
		
		public loanRequest()
}
