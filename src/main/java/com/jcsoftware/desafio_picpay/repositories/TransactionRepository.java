package com.jcsoftware.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcsoftware.desafio_picpay.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
