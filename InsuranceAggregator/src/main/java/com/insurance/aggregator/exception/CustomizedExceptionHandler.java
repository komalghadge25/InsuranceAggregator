package com.insurance.aggregator.exception;



import java.util.ArrayList;

import java.util.List;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice

public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError("Server Error", details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> methodArgumentExceptions(MethodArgumentTypeMismatchException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getErrorCode());
        ApiError error = new ApiError("Invalid Data", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
	
	/*@ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleHttpExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError("INVALID DATA", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }*/
	
 
    @ExceptionHandler(NoSuchRecordException.class)
    public final ResponseEntity<Object> handlePlanNotFound(NoSuchRecordException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError("Record Not Found", details);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ApiError error = new ApiError("Validation Failed", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
    
    
	/*
	 * public final ResponseEntity<Object>
	 * handleMessageNotReadable(HttpMessageNotReadableException ex, WebRequest
	 * request) {
	 * 
	 * ServletWebRequest servletWebRequest = (ServletWebRequest) request;
	 * log.info("{} to {}", servletWebRequest.getHttpMethod(),
	 * servletWebRequest.getRequest().getServletPath()); List<String> details = new
	 * ArrayList<>(); details.add(ex.getLocalizedMessage()); ApiError error = new
	 * ApiError("Invalid", details); return buildResponseEntity(new
	 * ApiError(error,HttpStatus.BAD_REQUEST)); }
	 */
    
   /* @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       // ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        //log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
       // return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }
    */
   
	/*
	 * private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	 * return buildResponseEntity(apiError);
	 * 
	 * }
	 */
}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
	 * handleAllExceptions(Exception ex, WebRequest request) { String error
	 * ="NOT FOUND";
	 * 
	 * //creating exception response structure ApiError apiError= new
	 * ApiError(HttpStatus.BAD_REQUEST, new Date(), error,
	 * request.getDescription(false));
	 * 
	 * //returning exception structure and specific status return new
	 * ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */
	
	
		
	/*
	 * @ExceptionHandler(NoSuchElementFound.class) public final
	 * ResponseEntity<Object> handleUserNotFoundExceptions(NoSuchElementFound ex,
	 * WebRequest request)throws Exception { String message ="NOT FOUND";
	 * 
	 * ApiError apiError= new ApiError(HttpStatus.NOT_FOUND, new Date(),
	 * message,request.getDescription(false));
	 * 
	 * return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND); }
	 */

	


