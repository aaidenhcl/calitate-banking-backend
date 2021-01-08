package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payment> getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(List<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", checkingBalance=" + checkingBalance + ", checkingInterestRate="
				+ checkingInterestRate + ", savingsBalance=" + savingsBalance + ", savingsInterestRate="
				+ savingsInterestRate + ", accountNumber=" + accountNumber + ", user=" + user + ", paymentHistory="
				+ paymentHistory + "]";
	}

	
}
