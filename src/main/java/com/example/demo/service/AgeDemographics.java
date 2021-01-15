package com.example.demo.service;

public class AgeDemographics {
	
	private String age;
	private Integer count;
	
	public AgeDemographics(String age, Integer count) {
		this.age = age;
		this.count = count;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
