package com.nt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TouristGlobalException {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArguException(IllegalArgumentException iae) {
		return new ResponseEntity<String>(iae.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<String> handleTouristNotFoundException(TouristNotFoundException tnfe) {
		return new ResponseEntity<String>(tnfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
