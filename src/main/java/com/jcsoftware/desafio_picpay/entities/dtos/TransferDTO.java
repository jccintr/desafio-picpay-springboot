package com.jcsoftware.desafio_picpay.entities.dtos;

import java.math.BigDecimal;

public record TransferDTO(Long payer, Long payee,BigDecimal value) {

}
