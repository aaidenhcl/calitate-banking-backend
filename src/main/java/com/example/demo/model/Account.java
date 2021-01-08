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
public class Account {

	@Id
	@GeneratedValue
	protected Long id;
	protected Double checkingBalance;
	protected Double checkingInterestRate;
	protected Double savingsBalance;
	protected Double savingsInterestRate;
	protected String accountNumber;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	@OneToMany(mappedBy = "account")
	List<Payment> paymentHistory;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCheckingBalance(Double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public Double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingInterestRate(Double checkingInterestRate) {
		this.checkingInterestRate = checkingInterestRate;
	}

	public Double getCheckingInterestRate() {
		return checkingInterestRate;
	}

	public void setSavingsBalance(Double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public Double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsInterestRate(Double savingsInterestRate) {
		this.savingsInterestRate = savingsInterestRate;
	}

	public Double getSavingsInterestRate() {
		return savingsInterestRate;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", interestRate=" + interestRate + ", accountNumber="
				+ accountNumber + "]";
	}


	
}
