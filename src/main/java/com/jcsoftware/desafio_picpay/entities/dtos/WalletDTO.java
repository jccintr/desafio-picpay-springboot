package com.jcsoftware.desafio_picpay.entities.dtos;

import java.math.BigDecimal;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.enums.WalletType;

public record WalletDTO(Long id,String fullName,String email,String doc,WalletType type,BigDecimal balance) {
	
	public WalletDTO(Wallet entity) {
		this(
				entity.getId(),
				entity.getFullName(),
				entity.getEmail(),
				entity.getDoc(),
				entity.getType(),
				entity.getBalance());
	}
}
