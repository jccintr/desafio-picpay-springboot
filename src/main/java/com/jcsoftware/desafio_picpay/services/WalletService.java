package com.jcsoftware.desafio_picpay.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.dtos.BalanceDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.NewWalletDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.TransferDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.TransferResponseDTO;
import com.jcsoftware.desafio_picpay.entities.dtos.WalletDTO;
import com.jcsoftware.desafio_picpay.repositories.DepositRepository;
import com.jcsoftware.desafio_picpay.repositories.WalletRepository;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedDocException;
import com.jcsoftware.desafio_picpay.services.exceptions.DuplicatedEmailException;
import com.jcsoftware.desafio_picpay.services.exceptions.ResourceNotFoundException;
import com.jcsoftware.desafio_picpay.services.exceptions.WalletNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WalletService {

	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private DepositRepository depositRepository;
	
	 @Autowired
	 private AuthorizationService authorizationService;
	
	

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
	public TransferResponseDTO transfer(TransferDTO dto) {

		Wallet payer = findWalletById(dto.payer());
		Wallet payee = findWalletById(dto.payee());
		
		if (payer.getBalance().compareTo(dto.value()) < 0) {
			return new TransferResponseDTO("Transação não Autorizada (saldo insuficiente");
		}
		
		if (!authorizationService.isAuthorized()) {
            return new TransferResponseDTO("Transação não Autorizada (autorização negada)");
        }
	    
       
        payer.setBalance(payer.getBalance().subtract(dto.value()));
        repository.save(payer);

        payee.setBalance(payee.getBalance().add(dto.value()));
        repository.save(payee);

        return new TransferResponseDTO("Transação Autorizada");
        
 	}
	
	private Wallet findWalletById(Long id) {
		 return repository.findById(id)
			        .orElseThrow(WalletNotFoundException::new);
	}
	
	
	
}
