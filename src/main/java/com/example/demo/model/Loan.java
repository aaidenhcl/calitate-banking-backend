package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue
	private Long id;
	private Double principle;
	private Float apr;
	private Integer termInMonths;
	private Double minimumMonthlyPayment;
	
	//Many to one user
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	//Has Many Payments
	@OneToMany(mappedBy="loan")
	List<Payment> paymentHistory;
}
