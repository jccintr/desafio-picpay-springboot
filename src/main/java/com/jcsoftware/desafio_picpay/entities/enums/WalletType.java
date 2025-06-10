package com.jcsoftware.desafio_picpay.entities.enums;

public enum WalletType {
	COMMON(1),
	MERCHANT(2);
	
	private int value;
	
	WalletType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
