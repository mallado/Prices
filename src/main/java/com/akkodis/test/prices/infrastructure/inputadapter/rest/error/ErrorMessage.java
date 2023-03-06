package com.akkodis.test.prices.infrastructure.inputadapter.rest.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
	
	private Date timestamp;
	private int statusCode;
	private String error;
	private String message;
	private String path;
	
}

