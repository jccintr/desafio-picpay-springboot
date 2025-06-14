package com.jcsoftware.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcsoftware.desafio_picpay.entities.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,Long>{

}
