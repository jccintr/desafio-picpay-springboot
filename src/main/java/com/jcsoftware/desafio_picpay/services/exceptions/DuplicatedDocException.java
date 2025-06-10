package com.jcsoftware.desafio_picpay.services.exceptions;

public class DuplicatedDocException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicatedDocException() {
		super("Documento já cadastrado");
	}

}
