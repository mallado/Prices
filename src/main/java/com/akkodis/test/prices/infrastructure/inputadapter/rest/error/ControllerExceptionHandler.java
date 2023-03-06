package com.akkodis.test.prices.infrastructure.inputadapter.rest.error;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
	
	  @ExceptionHandler(RestException.class)
	  public ResponseEntity<ErrorMessage> restException(RestException ex) {
		  
	    ErrorMessage message = new ErrorMessage(new Date(),
	    	ex.getHttpStatus().value(),
	        ex.getMessage(),
	        ex.getHttpStatus().name(),
	        ex.getPath());
	    return new ResponseEntity<>(message, ex.getHttpStatus());
	  }

}
