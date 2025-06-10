package com.jcsoftware.desafio_picpay.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jcsoftware.desafio_picpay.entities.dtos.NewWalletDTO;
import com.jcsoftware.desafio_picpay.services.WalletService;

@RestController
@RequestMapping(value = "/wallets")
public class WalletController {
	
	@Autowired
	private WalletService service;
	
	@PostMapping()
	public ResponseEntity<NewWalletDTO> insert(@RequestBody NewWalletDTO dto){
		NewWalletDTO walletDTO = service.insert(dto);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(walletDTO.id()).toUri();
			
			return ResponseEntity.created(uri).body(walletDTO);
	}

}
