package com.example.demo.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

	@Id
	@GeneratedValue
	protected Long id;
	protected Double balance;
	protected Double interestRate;
	protected String accountNumber;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	@OneToMany(mappedBy = "sendingAccount")
	List<Transfer> sentTransfers;

	@OneToMany(mappedBy = "receivingAccount")
	List<Transfer> receivingTransfers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", interestRate=" + interestRate + ", accountNumber="
				+ accountNumber + ", user=" + user + "]";
	}


	
}
