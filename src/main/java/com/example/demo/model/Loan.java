package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue
	private Long id;
	private Double principle;
	private Float apr;
	private Integer termInMonths;
	private Double minimumMonthlyPayment;
	
	//Many to one user
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	//Has Many Payments
	@OneToMany(mappedBy="loan")
	List<Payment> paymentHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrinciple() {
		return principle;
	}

	public void setPrinciple(Double principle) {
		this.principle = principle;
	}

	public Float getApr() {
		return apr;
	}

	public void setApr(Float apr) {
		this.apr = apr;
	}

	public Integer getTermInMonths() {
		return termInMonths;
	}

	public void setTermInMonths(Integer termInMonths) {
		this.termInMonths = termInMonths;
	}

	public Double getMinimumMonthlyPayment() {
		return minimumMonthlyPayment;
	}

	public void setMinimumMonthlyPayment(Double minimumMonthlyPayment) {
		this.minimumMonthlyPayment = minimumMonthlyPayment;
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
		return "Loan [id=" + id + ", principle=" + principle + ", apr=" + apr + ", termInMonths=" + termInMonths
				+ ", minimumMonthlyPayment=" + minimumMonthlyPayment + ", user=" + user + ", paymentHistory="
				+ paymentHistory + "]";
	}
	
	
	
}
