package com.example.demo.service;

public class AgeDemographics {
	
	private String age_range;
	private Integer total;
	
	public AgeDemographics(String age_range, Integer total) {
		this.age_range = age_range;
		this.total = total;
	}

	public String getAge_Range() {
		return age_range;
	}

	public void setAge_Range(String age_range) {
		this.age_range = age_range;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
