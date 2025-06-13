package com.jcsoftware.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcsoftware.desafio_picpay.entities.Deposit;

public interface DepositRepository extends JpaRepository<Deposit,Long>{

}
