package com.example.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

@Entity
public class CreditCardRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private Double offeredLimit;
	private Double offeredApr;
	private String cardType;

	@CreatedDate
	private Date requestTime;
	
	@LastModifiedDate
	private Date lastUpdated;
	private String reason;
	
	//many creditCardRequsts has one user
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;

	public CreditCardRequest() {}
	
	public CreditCardRequest(String cardType, User user) {
		super();
		this.cardType = cardType;
		this.user = user;
		this.requestTime = new Date();
		processCreditCardRequest();
	}
	
	/*
	 * credit card type is processed
	 * using the users credit score 
	 * to validate whether user is refused
	 * or not. 
	 * if credit score high enough the status
	 * updates to accepted
	 */
	public void processCreditCardRequest() {
		switch(this.cardType) {
		case "silver":
			if(this.user.getCreditScore().compareTo(500) >= 0) {
				this.status = "approved";
				this.offeredLimit = 1000d;
				this.offeredApr = 0.27d;
			} else {
				this.status = "refused";
				this.reason = "Credit score too low";
			}
			this.lastUpdated = new Date();			
			break;
		case "gold":
			if(this.user.getCreditScore().compareTo(650) >= 0) {
				//accepted
				this.status = "approved";
				this.offeredLimit = 10000d;
				this.offeredApr = 0.15d;
			} else {
				this.status = "refused";
				this.reason = "Credit score too low";
			}
			this.lastUpdated = new Date();			
			break;
		case "platinum":
			if(this.user.getCreditScore().compareTo(750) >= 0) {
				//accepted
				this.status = "approved";
				this.offeredLimit = 50000d;
				this.offeredApr = 0.07d;
			} else {
				this.status = "refused";
				this.reason = "Credit score too low";
			}
			this.lastUpdated = new Date();
			break;
		default:
			System.out.println("Not a valid card type");
			break;
		}
	}
	
	public CreditCard acceptOffer() {
		if(this.status.equals("approved")) {			
			System.out.println("Offer accepted");
			CreditCard creditCard = new CreditCard(this.offeredLimit, this.offeredApr, this.cardType, this.user);
			return creditCard;
		}
		System.out.println("You cannot accept this credit card");
		return null;
	}
	
	public void declineOffer() {
		System.out.println("Credit card offer was declined");
		this.status = "declined";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		this.requestTime = new Date();
		processCreditCardRequest();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getOfferedLimit() {
		return offeredLimit;
	}

	public void setOfferedLimit(Double offeredLimit) {
		this.offeredLimit = offeredLimit;
	}

	public Double getOfferedApr() {
		return offeredApr;
	}

	public void setOfferedApr(Double offeredApr) {
		this.offeredApr = offeredApr;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "CreditCardRequest [id=" + id + ", status=" + status + ", offeredLimit=" + offeredLimit + ", offeredApr="
				+ offeredApr + ", requestTime=" + requestTime + ", lastUpdated=" + lastUpdated + ", reason=" + reason
				+ "]";
	}

	


	
	
	
}
