package com.jcsoftware.desafio_picpay.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.dtos.NewWalletDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.WalletDTO;
import com.jcsoftware.desafio_picpay.repositories.WalletRepository;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedDocException;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedEmailException;
import com.jcsoftware.desafio_picpay.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

	public List<WalletDTO> findAll() {
		
		List<Wallet> wallets = repository.findAll();
		return wallets.stream().map(WalletDTO::new).toList();
		
	}

	public WalletDTO findById(Long id) {
		
		Optional<Wallet> walletO = repository.findById(id);
		Wallet wallet = walletO.orElseThrow(() -> new ResourceNotFoundException());
		WalletDTO dto = new WalletDTO(wallet);
		return dto;
		
	}

	@Transactional
	public WalletDTO deposit(Long id, BigDecimal value) {
		
		try {
			Wallet wallet = repository.getReferenceById(id);
			//copyDtoToEntity(dto, product);
			wallet.setBalance(wallet.getBalance().add(value));
			wallet = repository.save(wallet);
			return new WalletDTO(wallet);
		} catch (EntityNotFoundException e) {
			throw (new ResourceNotFoundException());
		}
		

	}
	
	
	
	
		
}
