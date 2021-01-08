package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(Double spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public Double getApr() {
		return apr;
	}

	public void setApr(Double apr) {
		this.apr = apr;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Spend> getSpendHistory() {
		return spendHistory;
	}

	public void setSpendHistory(List<Spend> spendHistory) {
		this.spendHistory = spendHistory;
	}

	public List<Payment> getPaymentHistory() {
		return paymentHistory;
	}

	public void setPaymentHistory(List<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", spendingLimit=" + spendingLimit + ", apr=" + apr + ", balance=" + balance
				+ ", expirationDate=" + expirationDate + ", creditCardNumber=" + creditCardNumber + ", cvv=" + cvv
				+ ", type=" + type + ", status=" + status + ", user=" + user + ", spendHistory=" + spendHistory
				+ ", paymentHistory=" + paymentHistory + "]";
	}
	
	
	
}
