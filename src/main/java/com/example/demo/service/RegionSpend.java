package com.example.demo.service;


public class RegionSpend {
	

	private String region;
	private Double amount_Spent;
	
	
	public RegionSpend(String region, Double amount_Spent){
		this.region = region;
		this.amount_Spent = amount_Spent;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Double getAmount_Spent() {
		return amount_Spent;
	}


	public void setAmount_Spent(Double amount_Spent) {
		this.amount_Spent = amount_Spent;
	}

	
}
