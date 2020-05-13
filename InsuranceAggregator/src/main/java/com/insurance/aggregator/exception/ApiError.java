package com.insurance.aggregator.exception;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;


@Data
public class ApiError {
	
	 public ApiError(String message, List<String> details) {
	        super();
	        this.message = message;
	        this.details = details;
	    }
	 
	    public ApiError(ApiError error, HttpStatus badRequest) {
		// TODO Auto-generated constructor stub
	}

		//General error message about nature of error
	    private String message;
	 
	    //Specific errors in API request processing
	    private List<String> details;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * private HttpStatus status; public ApiError(HttpStatus status, Date timestamp,
	 * String message, String details) { super(); this.status = status;
	 * this.timestamp = timestamp; this.message = message; this.details = details; }
	 * 
	 * @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	 * private Date timestamp; private String message; private String details;
	 * 
	 * 
	 * ApiError(HttpStatus status) {
	 * 
	 * this.status = status; }
	 * 
	 * public ApiError(HttpStatus notFound, Date timestamp2, String message2, Object
	 * details2) {
	 * 
	 * }
	 */
}
