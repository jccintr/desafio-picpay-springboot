package com.jcsoftware.desafio_picpay.services.exceptions;

public class WalletNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WalletNotFoundException() {
		super("Wallet not found");
	}

}