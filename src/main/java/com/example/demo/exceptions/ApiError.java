package com.example.demo.exceptions;

import java.util.Date;
import java.util.Map;

public class ApiError {
	
	private Date date;
	private String status;
	private Map<String,Object> errors;

	public ApiError(Date date, String status, Map<String, Object> errors) {
		this.date = date;
		this.status = status;
		this.errors = errors;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}

}