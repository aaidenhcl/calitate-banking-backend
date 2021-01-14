package com.example.demo.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.LastModifiedDate;


import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;

@Entity
public class LoanRequest {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String status;
		private Double offeredAmount;
		private float offeredApr;
		private Integer termInMonths;
		private Double minimumMonthlyPayment;
		
		@CreatedDate
		private Date requestedTime;
		@LastModifiedDate
		private Date updatedTime;
		
		private String reason;
		
		
		
		//many loanRequest has one user
		@ManyToOne
		@JoinColumn(name="user_id")
		public User user;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Double getOfferedAmount() {
			return offeredAmount;
		}

		public void setOfferedAmount(Double offeredAmount) {
			this.offeredAmount = offeredAmount;
		}

		public float getOfferedApr() {
			return offeredApr;
		}

		public void setOfferedApr(float offeredApr) {
			this.offeredApr = offeredApr;
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

		public Date getRequestedTime() {
			return requestedTime;
		}

		public void setRequestedTime(Date requestedTime) {
			this.requestedTime = requestedTime;
		}

		public Date getUpdatedTime() {
			return updatedTime;
		}

		public void setUpdatedTime(Date updatedTime) {
			this.updatedTime = updatedTime;
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
			return "loanRequest [id=" + id + ", status=" + status + ", offeredAmount=" + offeredAmount + ", offeredApr="
					+ offeredApr + ", termInMonths=" + termInMonths + ", minimumMonthlyPayment=" + minimumMonthlyPayment
					+ ", requestedTime=" + requestedTime + ", updatedTime=" + updatedTime + ", reason=" + reason
					+ ", user=" + user + "]";
		}



		
}
