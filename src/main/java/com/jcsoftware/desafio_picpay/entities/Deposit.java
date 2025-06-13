package com.jcsoftware.desafio_picpay.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

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
@Table(name = "deposits")
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	public Deposit() {
		
	}
	
	
	
	public Deposit(Long id, BigDecimal amount, Wallet wallet, Instant createdAt) {
		super();
		this.id = id;
		this.amount = amount;
		this.wallet = wallet;
		this.createdAt = createdAt;
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



	public Wallet getWallet() {
		return wallet;
	}



	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}



	public Instant getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}



	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deposit other = (Deposit) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
