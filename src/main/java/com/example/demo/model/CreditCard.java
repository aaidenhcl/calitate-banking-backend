package com.example.demo.model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
//	add has many spends
	@JsonIgnore
	@OneToMany(mappedBy = "creditCard")
	List<Spend> spendHistory;
	
	//has many payments
	@JsonIgnore
	@OneToMany(mappedBy = "creditCard")
	List<Payment> paymentHistory;
	
	public CreditCard () {}

	public CreditCard(Double spendingLimit, Double apr, String type, User user) {
		super();
		this.spendingLimit = spendingLimit;
		this.apr = apr;
		this.type = type;
		this.user = user;
		this.dateCreated = new Date();
		this.lastUpdate = new Date();
		this.balance = 0.0d;
		this.status = "active";
		try {			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.YEAR, 5);
			this.expirationDate = c.getTime();
//			this.expirationDate = Date.from(Instant.now().plus(5l, ChronoUnit.YEARS));//use date to generate current date + 5 years
		}catch (Exception e) {
			System.err.println("Issue with CHRONO"+e);
		}
		this.cvv = generateRandomNumber(3);//some random 3 digit number
		this.creditCardNumber = generateRandomNumber(16);//some random 16 digit num
		
	}
	
	private String generateRandomNumber(Integer length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		for(Integer i=0; i<=length; i++) {
			sb.append(random.nextInt(10));
		}
		System.out.println("GENERATED NUMBER::: "+sb.toString());
		return sb.toString();
	}

	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}



	public Date getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



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
				+ ", type=" + type + ", status=" + status + ", dateCreated=" + dateCreated + ", lastUpdate="
				+ lastUpdate + ", user=" + user + ", spendHistory=" + spendHistory + ", paymentHistory="
				+ paymentHistory + "]";
	}


	
	
	
}
