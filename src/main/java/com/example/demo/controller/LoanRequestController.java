package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.LoanRequestBO;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class LoanRequestController {

	@Autowired
	LoanRequestBO bo;
	
}
