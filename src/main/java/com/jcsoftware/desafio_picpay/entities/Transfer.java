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
@Table(name = "transfers")
public class Transfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payer_id")
	private Wallet walletPayer;
	
	@ManyToOne
	@JoinColumn(name = "wallet_payee_id")
	private Wallet walletPayee;
	
	@Column(columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	public Transfer() {
		
	}

	public Transfer(Long id, BigDecimal amount, Wallet walletPayer, Wallet walletPayee, Instant createdAt) {
		super();
		this.id = id;
		this.amount = amount;
		this.walletPayer = walletPayer;
		this.walletPayee = walletPayee;
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

	public Wallet getWalletPayer() {
		return walletPayer;
	}

	public void setWalletPayer(Wallet walletPayer) {
		this.walletPayer = walletPayer;
	}

	public Wallet getWalletPayee() {
		return walletPayee;
	}

	public void setWalletPayee(Wallet walletPayee) {
		this.walletPayee = walletPayee;
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
		Transfer other = (Transfer) obj;
		return Objects.equals(id, other.id);
	}
	
	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();
	}

}
