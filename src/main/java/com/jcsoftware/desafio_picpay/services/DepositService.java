package com.jcsoftware.desafio_picpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcsoftware.desafio_picpay.entities.Deposit;
import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.dtos.BalanceDTO;
import com.jcsoftware.desafio_picpay.repositories.DepositRepository;
import com.jcsoftware.desafio_picpay.repositories.WalletRepository;
import com.jcsoftware.desafio_picpay.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DepositService {
	
	@Autowired
	private DepositRepository repository;
	@Autowired
	private WalletRepository walletRepository;
	
	@Transactional
	public BalanceDTO insert(Long id, BigDecimal value) {
		
		try {
			Wallet wallet = walletRepository.getReferenceById(id);
			wallet.setBalance(wallet.getBalance().add(value));
			wallet = walletRepository.save(wallet);
			Deposit newDeposit = new Deposit();
			newDeposit.setAmount(value);
			newDeposit.setWallet(wallet);
			repository.save(newDeposit);
			return new BalanceDTO(wallet.getBalance());
		} catch (EntityNotFoundException e) {
			throw (new ResourceNotFoundException());
		}
		

	}

}
