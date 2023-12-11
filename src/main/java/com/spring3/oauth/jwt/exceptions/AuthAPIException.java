package com.spring3.oauth.jwt.exceptions;

import org.springframework.http.HttpStatus;

public class AuthAPIException extends RuntimeException  {
    /**
     * 
     */
    private static final long serialVersionUID = 3626166857988734241L;
    private HttpStatus status;
    private String message;

    public AuthAPIException(HttpStatus status, String message) {
	super(message);
	this.status = status;
	this.message = message;
    }

    public AuthAPIException(String message, HttpStatus status,
                            String message1) {
	super(message + message1);
	this.status = status;
	this.message = message1;
    }

    public HttpStatus getStatus() {
	return status;
    }

    @Override
    public String getMessage() {
	return message;
    }
}
