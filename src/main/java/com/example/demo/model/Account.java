package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;

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
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdated;
	
	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}



	public Date getLastUpdated() {
		return lastUpdated;
	}



	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	@OneToMany(mappedBy = "account")
	List<Payment> paymentHistory;
	
	public Account() {}
	
	
	
	public Account(Double checkingBalance, Double checkingInterestRate, Double savingsBalance,
			Double savingsInterestRate, String accountNumber, User user) {
		super();
		this.checkingBalance = checkingBalance;
		this.checkingInterestRate = checkingInterestRate;
		this.savingsBalance = savingsBalance;
		this.savingsInterestRate = savingsInterestRate;
		this.accountNumber = accountNumber;
		this.dateCreated = new Date();
		this.lastUpdated = new Date();
		this.user = user;
	}



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
				+ savingsInterestRate + ", accountNumber=" + accountNumber + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", user=" + user + ", paymentHistory=" + paymentHistory + "]";
	}
	
}
