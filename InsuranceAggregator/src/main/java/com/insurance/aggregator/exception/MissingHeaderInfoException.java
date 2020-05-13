package com.insurance.aggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND) 
public class MissingHeaderInfoException extends RuntimeException{
    private static final long serialVersionUID = 1L;
 
    public MissingHeaderInfoException(String message) {
        super(message);
    } 
 }
