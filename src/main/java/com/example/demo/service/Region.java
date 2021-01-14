package com.example.demo.service;


public class Region{
	

	private String region;
	private Double amount;
	private Long sale;
	
	public Region(String region, Double amount, Long sale){
		this.region = region;
		this.amount = amount;
		this.sale = sale;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Long getSale() {
		return sale;
	}


	public void setSale(Long sale) {
		this.sale = sale;
	}


	
}
