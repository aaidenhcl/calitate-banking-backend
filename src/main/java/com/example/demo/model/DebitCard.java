package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class DebitCard {
	
	@Id
	@GeneratedValue
	private Long id;
	private String card_number;
	private Date expiration_date;
	private String cvv;
	
	//Debit has one account
	@ManyToOne
	@JoinColumn(name="account_id")
	Account account;
	
	//Debit has many spend
	@OneToMany(mappedBy ="debitCard")
	List<Spend> spendHistory;
	
	
	
}
