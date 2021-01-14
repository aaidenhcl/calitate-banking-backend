package com.example.demo.service;


public class RegionSale{

	private String region;
	private Long sale;
	
	
	public RegionSale(String region, Long sale){
		this.region = region;
		this.sale = sale;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Long getSale() {
		return sale;
	}


	public void setSale(Long sale) {
		this.sale = sale;
	}



}
