package com.jcsoftware.desafio_picpay.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.jcsoftware.desafio_picpay.entities.dtos.FieldMessage;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		
		super(timestamp, status, error, message, path);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void AddError(String fieldName,String message) {
		errors.removeIf(x -> x.getFieldName().equals(fieldName));
		errors.add(new FieldMessage(fieldName,message));		
	}
	
	
}
