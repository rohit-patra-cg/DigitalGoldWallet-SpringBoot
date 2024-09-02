package com.example.demo.dto;

import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransactionHistoryDTO {

	@NotNull(message = "User ID is required")
	private Integer userId;

	@NotNull(message = "Branch ID is required")
	private Integer branchId;

	@NotNull(message = "Transaction Type is required")
	private TxnHistoryTransactionType transactionType;

	@NotNull(message = "Transaction Status is required")
	private TransactionStatus transactionStatus;

	@DecimalMin(value = "0.0", message = "Quantity must be equal to or more than 0")
	private Double quantity;

	@DecimalMin(value = "0.0", message = "Amount must be equal to or more than 0")
	private Double amount;

	public TransactionHistoryDTO(Integer userId, Integer branchId, TxnHistoryTransactionType transactionType,
			TransactionStatus transactionStatus, Double quantity, Double amount) {
		this.userId = userId;
		this.branchId = branchId;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
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

}
