package com.example.demo.service;


public class RegionSale{

	private String region;
	private Long cards_Sold;
	
	
	public RegionSale(String region, Long cards_Sold){
		this.region = region;
		this.cards_Sold = cards_Sold;
	}


	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public Long getCards_Sold() {
		return cards_Sold;
	}


	public void setCards_Sold(Long cards_Sold) {
		this.cards_Sold = cards_Sold;
	}



}
