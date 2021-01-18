package com.example.demo.service;

public class DemographicsRegion {
	
	private String region;
	private Long users_in_region;
	
	public DemographicsRegion(String region, Long users_in_region) {
		this.region = region;
		this.users_in_region = users_in_region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getUsers_in_region() {
		return users_in_region;
	}

	public void setUsers_in_region(Long users_in_region) {
		this.users_in_region = users_in_region;
	}

	

}