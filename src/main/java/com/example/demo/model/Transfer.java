package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Transfer {
	
	@Id
	@GeneratedValue
	protected Long id;
    protected Double amount;
	protected Date transferDate;

    @ManyToOne
    @JoinColumn(name="sendingAccount_id")
    protected Account sendingAccount;
    
    @ManyToOne
    @JoinColumn(name="receivingAccount_id")
    protected Account receivingAccount;
	
    
    
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

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Account getSendingAccount() {
		return sendingAccount;
	}

	public void setSendingAccount(Account sendingAccount) {
		this.sendingAccount = sendingAccount;
	}

	public Account getReceivingAccount() {
		return receivingAccount;
	}

	public void setReceivingAccount(Account receivingAccount) {
		this.receivingAccount = receivingAccount;
	}



}
