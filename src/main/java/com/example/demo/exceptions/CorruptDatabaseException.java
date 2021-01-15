package com.example.demo.exceptions;

public class CorruptDatabaseException extends Exception{
	
	public CorruptDatabaseException() {
		super();
	}
	
	public CorruptDatabaseException(String message) {
		super(message);
	}
}