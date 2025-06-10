package com.jcsoftware.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcsoftware.desafio_picpay.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

	public Wallet findByEmail(String email);
	public Wallet findByDoc(String doc);
}
