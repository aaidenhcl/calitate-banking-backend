package com.example.demo.service;

public class Demographics {
	
	private String category;
	private Long users;
	
	public Demographics(String category, Long users) {
		this.category = category;
		this.users = users;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getUsers() {
		return users;
	}

	public void setUsers(Long users) {
		this.users = users;
	}

	

}
