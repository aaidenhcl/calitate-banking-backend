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
public class creditCardRequest {

	@Id
	@GeneratedValue
	private Long id;
	private String status;
	private Double offeredLimit;
	private Double offeredApr;
	
	@CreatedDate
	private Date requestTime;
	
	@LastModifiedDate
	private Date lastUpdated;
	private String reason;
	
	//many creditCardRequsts has one user
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;

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

	@Override
	public String toString() {
		return "creditCardRequest [id=" + id + ", status=" + status + ", offeredLimit=" + offeredLimit + ", offeredApr="
				+ offeredApr + ", requestTime=" + requestTime + ", lastUpdated=" + lastUpdated + ", reason=" + reason
				+ ", user=" + user + "]";
	}


	
	
	
}
