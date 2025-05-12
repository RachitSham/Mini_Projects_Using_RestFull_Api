package com.nt.globalexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ActorGlobalException {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ae) {
		return new ResponseEntity<String>(ae.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ActorNotFoundException.class)
	public ResponseEntity<String> handleTouristNotFoundException(ActorNotFoundException fe) {
		return new ResponseEntity<String>(fe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
