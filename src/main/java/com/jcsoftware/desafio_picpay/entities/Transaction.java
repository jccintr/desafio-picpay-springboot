package com.jcsoftware.desafio_picpay.entities;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private BigDecimal amount;
	@ManyToOne
	@JoinColumn(name = "payer_id")
	private Wallet payer;
	@ManyToOne
	@JoinColumn(name = "payee_id")
	private Wallet payee;
	@Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	public Transaction() {
		
	}
	
	
	
	public Transaction(Long id, BigDecimal amount, Wallet payer, Wallet payee) {
		super();
		this.id = id;
		this.amount = amount;
		this.payer = payer;
		this.payee = payee;
	}
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public Wallet getPayer() {
		return payer;
	}



	public void setPayer(Wallet payer) {
		this.payer = payer;
	}



	public Wallet getPayee() {
		return payee;
	}



	public void setPayee(Wallet payee) {
		this.payee = payee;
	}



	public Instant getCreatedAt() {
		return createdAt;
	}



	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
	}

}
