package com.example.demo.service;

public class CreditCardDiscontinued {
	
	
	private Long credit_Cards_Discontinued;
	private String region;
	private String profession;
	
	public CreditCardDiscontinued(Long credit_Cards_Discontinued, String region, String profession) {
		this.credit_Cards_Discontinued = credit_Cards_Discontinued;
		this.region = region;
		this.profession = profession;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public Long getCredit_Cards_Discontinued() {
		return credit_Cards_Discontinued;
	}
	public void setCredit_Cards_Discontinued(Long credit_Cards_Discontinued) {
		this.credit_Cards_Discontinued = credit_Cards_Discontinued;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	

}
