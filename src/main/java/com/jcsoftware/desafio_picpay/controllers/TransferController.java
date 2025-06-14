package com.jcsoftware.desafio_picpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcsoftware.desafio_picpay.entities.dtos.TransferDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.TransferResponseDTO;
import com.jcsoftware.desafio_picpay.services.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/transfer")
public class TransferController {
	
	@Autowired
	private WalletService service;

	
	@PostMapping()
	public ResponseEntity<TransferResponseDTO> transfer(@RequestBody @Valid TransferDTO dto){
		TransferResponseDTO response = service.transfer(dto);
	    return ResponseEntity.ok().body(response);
	}
	
}
