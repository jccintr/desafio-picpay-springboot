package com.jcsoftware.desafio_picpay.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jcsoftware.desafio_picpay.entities.dtos.BalanceDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.DepositDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.NewWalletDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.WalletDTO;
import com.jcsoftware.desafio_picpay.services.DepositService;
import com.jcsoftware.desafio_picpay.services.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/wallets")
public class WalletController {
	
	@Autowired
	private WalletService service;
	
	@Autowired
	private DepositService depositService;
	
	@PostMapping()
	public ResponseEntity<NewWalletDTO> insert(@RequestBody @Valid NewWalletDTO dto){
		NewWalletDTO walletDTO = service.insert(dto);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(walletDTO.id()).toUri();
			
			return ResponseEntity.created(uri).body(walletDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<WalletDTO>> findAll(){
		List<WalletDTO> wallets = service.findAll();
        return ResponseEntity.ok().body(wallets);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WalletDTO> findById(@PathVariable Long id){
		WalletDTO walletDTO = service.findById(id);
		return ResponseEntity.ok().body(walletDTO);
	}
	
	@PostMapping(value="/{id}/deposit")
	public ResponseEntity<BalanceDTO> deposit(@PathVariable Long id,@RequestBody @Valid DepositDTO dto){
		BalanceDTO balance = depositService.insert(id,dto.value());
		return ResponseEntity.ok().body(balance);
	}
}
