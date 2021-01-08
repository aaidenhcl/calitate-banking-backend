package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;

@Entity
public class DebitCard {
	
	@Id
	@GeneratedValue
	private Long id;
	private String card_number;
	private Date expiration_date;
	private String cvv;
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdate;
	
	//Debit has one account
	@ManyToOne
	@JoinColumn(name="account_id")
	Account account;
	
	//Debit has many spend
	@OneToMany(mappedBy ="debitCard")
	List<Spend> spendHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Spend> getSpendHistory() {
		return spendHistory;
	}

	public void setSpendHistory(List<Spend> spendHistory) {
		this.spendHistory = spendHistory;
	}

	@Override
	public String toString() {
		return "DebitCard [id=" + id + ", card_number=" + card_number + ", expiration_date=" + expiration_date
				+ ", cvv=" + cvv + ", dateCreated=" + dateCreated + ", lastUpdate=" + lastUpdate + ", account="
				+ account + ", spendHistory=" + spendHistory + "]";
	}


	
	
	
}
