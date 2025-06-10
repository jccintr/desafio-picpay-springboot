package com.jcsoftware.desafio_picpay.entities;

import java.util.Objects;

import com.jcsoftware.desafio_picpay.entities.enums.WalletType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String doc;
	private String password;
	private WalletType type;
	
	public Wallet() {
		
	}

	public Wallet(Long id, String fullName, String email, String doc, String password, WalletType walletType) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.doc = doc;
		this.password = password;
		this.type = walletType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WalletType getWalletType() {
		return type;
	}

	public void setWalletType(WalletType walletType) {
		this.type = walletType;
	}
	
	/*
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		
		if(orderStatus != null) {
			this.status = orderStatus.getCode();			
		}
	}
*/
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
		Wallet other = (Wallet) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	


}
