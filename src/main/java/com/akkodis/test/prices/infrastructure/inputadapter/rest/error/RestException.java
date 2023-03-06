package com.akkodis.test.prices.infrastructure.inputadapter.rest.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RestException extends Exception {

	private static final long serialVersionUID = 2646492212496445879L;

	private final HttpStatus httpStatus;
	private final String path;

	
	public RestException(HttpStatus httpStatus, Exception e, String path) {
		super(e.getMessage(), e, false, false);
		this.httpStatus = httpStatus;
		this.path = path;
	}
}
