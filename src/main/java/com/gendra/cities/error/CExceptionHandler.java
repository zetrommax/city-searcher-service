package com.gendra.cities.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class CExceptionHandler {
	
	@ExceptionHandler({NumberFormatException.class})
	public ResponseEntity<Map<String, String>> requestMethod(NumberFormatException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error",ex.getMessage());
		errorResponse.put("message","The value is not valid. Introduce a valid coordinate.");
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Map<String, String>> genericError(Exception ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error",ex.getMessage());
		errorResponse.put("message","Please fill out the parameters q, longitude or latitude to look for");
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}

}
