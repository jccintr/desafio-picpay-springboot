package com.jcsoftware.desafio_picpay.services.exceptions;

public class InvalidOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidOperationException() {
		super("Operation denied for Merchant Wallet");
	}

}