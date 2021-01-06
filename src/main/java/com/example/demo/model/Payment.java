package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue
	private Long id;
	private Double amount;
	private String item;
	
	//belongs to credit card
	@ManyToOne
	@JoinColumn(name = "credit_card_id")
	CreditCard creditCard;
	
	//belongs to loan
	@ManyToOne
	@JoinColumn(name="loan_id")
	Loan loan;
	
	
	//belongs to account
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;
	
}
