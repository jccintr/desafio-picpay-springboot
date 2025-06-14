package com.jcsoftware.desafio_picpay.entities.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DepositDTO(
		@NotNull(message="Campo requerido")
		@Positive(message = "O valor deve ser maior do que zero.")
		BigDecimal value
		) {

}
