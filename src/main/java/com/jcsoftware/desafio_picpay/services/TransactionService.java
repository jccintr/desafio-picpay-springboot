package com.jcsoftware.desafio_picpay.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcsoftware.desafio_picpay.entities.Deposit;
import com.jcsoftware.desafio_picpay.entities.Transaction;
import com.jcsoftware.desafio_picpay.entities.Transfer;
import com.jcsoftware.desafio_picpay.entities.Wallet;
import com.jcsoftware.desafio_picpay.entities.enums.TransactionType;
import com.jcsoftware.desafio_picpay.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	@Transactional
	public void saveDeposit(Wallet wallet,Deposit deposit, BigDecimal amount) {
		Transaction t = new Transaction();
		t.setWallet(wallet);
		t.setAmount(amount);
		t.setDescription("Deposit Operation");
		t.setDeposit(deposit);
		t.setType(TransactionType.DEPOSIT);
		repository.save(t);
	}
	@Transactional
	public void saveTransfer(Transfer transfer) {
		this.saveDebitOperation(transfer);
		this.saveCreditOperation(transfer);
	}
	@Transactional
	private void saveDebitOperation(Transfer transfer) {
		Transaction t = new Transaction();
		t.setWallet(transfer.getWalletPayer());
		t.setAmount(transfer.getAmount());
		t.setDescription("Transfer to " + transfer.getWalletPayee().getFullName());
		t.setTransfer(transfer);
		t.setType(TransactionType.DEBIT);
		repository.save(t);
	}
	
	@Transactional
	private void saveCreditOperation(Transfer transfer) {
		Transaction t = new Transaction();
		t.setWallet(transfer.getWalletPayee());
		t.setAmount(transfer.getAmount());
		t.setDescription("Transfer from " + transfer.getWalletPayer().getFullName());
		t.setTransfer(transfer);
		t.setType(TransactionType.CREDIT);
		repository.save(t);
	}
	
}
