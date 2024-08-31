package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	private VendorBranch branch;

	@Enumerated(EnumType.STRING)
	private TxnHistoryTransactionType transactionType;

	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;

	@DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be positive")
	private Double quantity;

	@DecimalMin(value = "0.0", inclusive = true, message = "Amount must be positive")
	private Double amount;

	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VendorBranch getBranch() {
		return branch;
	}

	public void setBranch(VendorBranch branch) {
		this.branch = branch;
	}

	public TxnHistoryTransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TxnHistoryTransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
