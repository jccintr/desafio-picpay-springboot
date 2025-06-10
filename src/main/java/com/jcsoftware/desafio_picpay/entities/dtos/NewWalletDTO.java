package com.jcsoftware.desafio_picpay.entities.dtos;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.enums.WalletType;

public record NewWalletDTO(Long id,String fullName,String email,String doc,String password,WalletType type) {
	
	public NewWalletDTO(Wallet entity) {
		this(
				entity.getId(),
				entity.getFullName(),
				entity.getEmail(),
				entity.getDoc(),
				entity.getPassword(),
				entity.getType());
	}

}
