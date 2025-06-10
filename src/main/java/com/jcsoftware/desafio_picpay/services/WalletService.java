package com.jcsoftware.desafio_picpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.dtos.NewWalletDTO;
import com.jcsoftware.desafio_picpay.repositories.WalletRepository;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedDocException;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedEmailException;

@Service
public class WalletService {

	@Autowired
	private WalletRepository repository;

	public NewWalletDTO insert(NewWalletDTO dto) {
		
				
		
		var wallet = repository.findByEmail(dto.email());
		
		if(wallet!=null) {
			throw new DuplicatedEmailException();
		}
		
		wallet = repository.findByDoc(dto.doc());
		if(wallet!=null) {
			throw new DuplicatedDocException();
		}
		
		var newWallet = new Wallet();
		newWallet.setFullName(dto.fullName());
		newWallet.setEmail(dto.email());
		newWallet.setDoc(dto.doc());
		newWallet.setPassword(dto.password());
		newWallet.setBalance(new BigDecimal("0.00"));
		newWallet.seType(dto.type());
		
		newWallet = repository.save(newWallet);
		
		return new NewWalletDTO(newWallet);
	}
}
