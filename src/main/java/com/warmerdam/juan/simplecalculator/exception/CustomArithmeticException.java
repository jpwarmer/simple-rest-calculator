package com.warmerdam.juan.simplecalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomArithmeticException extends RuntimeException {
	
	public CustomArithmeticException() {
		super();
	}
	
	public CustomArithmeticException(String message) {
		super(message);
	}
	
	public CustomArithmeticException(String message, Throwable cause) {
		super(message, cause);
	}
}
