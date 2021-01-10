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
public class Spend {

	@Id
	@GeneratedValue
	private Long id;
	private Double amount;
	private String category;
	private String item;
	
	@CreatedDate
	protected Date dateCreated;
	
	@LastModifiedDate
	protected Date lastUpdate;
	
	public Date getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@ManyToOne
	@JoinColumn(name="credit_card_id")
	public CreditCard creditCard;
	
	
	@ManyToOne
	@JoinColumn(name="debit_card_id")
	public DebitCard debitCard;



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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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


	public DebitCard getDebitCard() {
		return debitCard;
	}


	public void setDebitCard(DebitCard debitCard) {
		this.debitCard = debitCard;
	}


	@Override
	public String toString() {
		return "Spend [id=" + id + ", amount=" + amount + ", category=" + category + ", item=" + item + ", dateCreated="
				+ dateCreated + ", lastUpdate=" + lastUpdate + ", creditCard=" + creditCard + ", debitCard=" + debitCard
				+ "]";
	}



	
}
