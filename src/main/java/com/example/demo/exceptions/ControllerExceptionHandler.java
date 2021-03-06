package com.example.demo.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex, HttpHeaders headers, 
	        HttpStatus status, WebRequest request) {

	    Map<String, Object> errors = new HashMap<String,Object>();
	    
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    return ResponseEntity.badRequest().body(new ApiError(new Date(),"failure",errors));
	}

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
    		ConstraintViolationException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getLocalizedMessage());

        return ResponseEntity.badRequest().body(new ApiError(new Date(),"failure",body));
    }
    
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Object> NotAuthorizedException(
    		NotAuthorizedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "User is not authorized");

        return ResponseEntity.badRequest().body(new ApiError(new Date(),"failure",body));
    }
    
    @ExceptionHandler(CorruptDatabaseException.class)
    public ResponseEntity<Object> CorruptDatabaseException(
    		CorruptDatabaseException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "values for credit card status and/or spending_limit cannot be null. check your records");

        return ResponseEntity.badRequest().body(new ApiError(new Date(),"failure",body));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllOtherException(
    		Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "System is not available. Please try after sometime. Your patience is appreciated");

        return ResponseEntity.badRequest().body(new ApiError(new Date(),"failure",body));
    }
}
