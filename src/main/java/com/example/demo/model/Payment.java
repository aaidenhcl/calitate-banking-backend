package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private String item;
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdate;
	
	//belongs to credit card
	@ManyToOne
	@JoinColumn(name = "credit_card_id")
	CreditCard creditCard;
	
	//belongs to loan
	@ManyToOne
	@JoinColumn(name="loan_id")
	Loan loan;
	
	
	//belongs to account
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public CreditCard getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	public Loan getLoan() {
		return loan;
	}


	public void setLoan(Loan loan) {
		this.loan = loan;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", item=" + item + ", dateCreated=" + dateCreated
				+ ", lastUpdate=" + lastUpdate + ", creditCard=" + creditCard + ", loan=" + loan + ", account="
				+ account + "]";
	}



	
	
	
	
}
