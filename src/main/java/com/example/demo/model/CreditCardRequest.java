package com.example.demo.model;

import javax.persistence.GeneratedValue;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;

@Entity
public class CreditCardRequest {
	
	@Id
	@GeneratedValue
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
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;

	public CreditCardRequest() {}
	
	public CreditCardRequest(String cardType, User user) {
		super();
		this.cardType = cardType;
		this.user = user;
		this.requestTime = new Date();
	}
	
	/*
	 * credit card type is processed
	 * using the users credit score 
	 * to validate whether user is refused
	 * or not. 
	 * if credit score high enough the status
	 * updates to accepted
	 */
	public CreditCardRequest processCreditCardRequest() {
		switch(this.cardType) {
		case "silver":
			if(this.user.getCreditScore().compareTo(500) > 0) {
				this.status = "approved";
				this.offeredLimit = 1000d;
				
			}
			
			break;
		case "gold":
			if(this.user.getCreditScore().compareTo(650) > 0) {
				//accepted
			}else {
				this.status = "refused";
			}
			
			break;
		case "platinum":
			if(this.user.getCreditScore().compareTo(500) > 0) {
				//accepted
			}

			break;
		default:
			System.out.println("Not a valid card type");
			break;
		}
		return this;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "creditCardRequest [id=" + id + ", status=" + status + ", offeredLimit=" + offeredLimit + ", offeredApr="
				+ offeredApr + ", requestTime=" + requestTime + ", lastUpdated=" + lastUpdated + ", reason=" + reason
				+ ", user=" + user + "]";
	}


	
	
	
}
