package com.example.demo.service;

public class DemographicsProfession {
	
	private String profession;
	private Long users_in_profession;
	
	public DemographicsProfession(String profession, Long users_in_profession) {
		this.profession = profession;
		this.users_in_profession = users_in_profession;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getUsers_in_profession() {
		return users_in_profession;
	}

	public void setUsers_in_profession(Long users_in_profession) {
		this.users_in_profession = users_in_profession;
	}



	

}