package com.example.demo.service;


public class RegionSpend {
	

	private String region;
	private Double amount;
	
	
	public RegionSpend(String region, Double amount){
		this.region = region;
		this.amount = amount;
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

	
}
