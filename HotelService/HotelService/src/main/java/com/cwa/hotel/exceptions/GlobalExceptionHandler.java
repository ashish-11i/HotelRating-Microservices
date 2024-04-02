package com.cwa.hotel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		Map<String, Object> map = new HashMap<>();
		map.put("message", message);
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
}
