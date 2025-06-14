package com.jcsoftware.desafio_picpay.entities.dtos;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.enums.WalletType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewWalletDTO(
		Long id,
		@NotNull(message="Campo requerido")
		String fullName,
		@NotNull(message="Campo requerido")
		@Email(message="Email inválido",regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
		String email,
		@NotNull(message="Campo requerido")
		String doc,
		@NotBlank(message = "Campo requerido")
		String password,
		@NotNull(message="Campo requerido")
		WalletType type) {
	
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
