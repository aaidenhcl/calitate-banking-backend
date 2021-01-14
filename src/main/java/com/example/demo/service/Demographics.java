package com.example.demo.service;

public class Demographics {
	
	private String profession;
	private Long count;
	
	public Demographics(String profession, Long count) {
		this.profession = profession;
		this.count = count;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	

}
