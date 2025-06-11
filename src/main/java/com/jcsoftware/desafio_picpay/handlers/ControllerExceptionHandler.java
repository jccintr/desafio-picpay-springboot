package com.jcsoftware.desafio_picpay.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jcsoftware.desafio_picpay.controllers.exceptions.StandardError;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedDocException;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedEmailException;
import com.jcsoftware.desafio_picpay.services.exceptions.InvalidOperationException;
import com.jcsoftware.desafio_picpay.services.exceptions.ResourceNotFoundException;
import com.jcsoftware.desafio_picpay.services.exceptions.WalletNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(DuplicatedEmailException.class)
	public ResponseEntity<StandardError> resourceNotFound(DuplicatedEmailException e,HttpServletRequest request){
		
		String error = "Conflict";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(DuplicatedDocException.class)
	public ResponseEntity<StandardError> resourceNotFound(DuplicatedDocException e,HttpServletRequest request){
		
		String error = "Conflict";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request){
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(WalletNotFoundException.class)
	public ResponseEntity<StandardError> walletNotFound(WalletNotFoundException e,HttpServletRequest request){
		
		String error = "Wallet not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<StandardError> operationIvalid(InvalidOperationException e,HttpServletRequest request){
		
		String error = "Invalid operation for payer";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
