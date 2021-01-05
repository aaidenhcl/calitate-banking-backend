package com.example.demo.model;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
//@Component
//@Scope(value="prototype")
public class CommercialUser extends User{

	public CommercialUser(String username, String password) {
		super(username, password);
	}

}
