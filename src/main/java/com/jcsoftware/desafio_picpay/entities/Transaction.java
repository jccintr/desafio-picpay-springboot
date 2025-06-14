package com.jcsoftware.desafio_picpay.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import com.jcsoftware.desafio_picpay.entities.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	private String description;
	private BigDecimal amount;
	

	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@ManyToOne
	@JoinColumn(name = "deposit_id")
	private Deposit deposit;
	
	@ManyToOne
	@JoinColumn(name = "transfer_id")
	private Transfer transfer;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	public Transaction() {
		
	}
	
	


	public Transaction(Long id, String description, BigDecimal amount, Wallet wallet, Deposit deposit,
			Transfer transfer, TransactionType type, Instant createdAt) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.wallet = wallet;
		this.deposit = deposit;
		this.transfer = transfer;
		this.type = type;
		this.createdAt = createdAt;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
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




	public Deposit getDeposit() {
		return deposit;
	}




	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}




	public Transfer getTransfer() {
		return transfer;
	}




	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}




	public TransactionType getType() {
		return type;
	}




	public void setType(TransactionType type) {
		this.type = type;
	}




	public Instant getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
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
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}




	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
	}

	

	
	
	

}
