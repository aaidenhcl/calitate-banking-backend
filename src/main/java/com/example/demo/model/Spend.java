package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Spend {

	@Id
	@GeneratedValue
	private Long id;
	private Double amount;
	private String category;
	private String item;
	
	@ManyToOne
	@JoinColumn(name="credit_card_id")
	public CreditCard creditCard;
	
	
	@ManyToOne
	@JoinColumn(name="debit_card_id")
	public DebitCard debitCard;
}
