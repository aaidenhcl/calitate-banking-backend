package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope(value="prototype")
@Entity 
public class ConsumerUser extends User{

//	public ConsumerUser() {}
	
	public ConsumerUser(String username, String password) {
		super(username, password);
	}

	public ConsumerUser() {}
	
}
