package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue
	private Long id;
	private Double spendingLimit;
	private Double apr;
	private Double balance;
	private Date expirationDate;
	private String creditCardNumber;
	private String cvv;
	private String type;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
//	add has many spends
	@OneToMany(mappedBy = "creditCard")
	List<Spend> spendHistory;
	
	//has many payments
	@OneToMany(mappedBy = "creditCard")
	List<Payment> paymentHistory;
	
	public CreditCard () {}
	
}
