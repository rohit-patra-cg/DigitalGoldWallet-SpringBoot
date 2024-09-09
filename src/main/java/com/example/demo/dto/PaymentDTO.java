package com.example.demo.dto;

import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.enums.PaymentTransactionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentDTO {
	@Positive(message = "Amount must be positive")
	private final Double amount;

	@NotNull(message = "Payment Method is required")
	@Enumerated(EnumType.STRING)
	private final PaymentMethod paymentMethod;

	@NotNull(message = "Transaction Type is required")
	@Enumerated(EnumType.STRING)
	private final PaymentTransactionType transactionType;

	@NotNull(message = "Payment Status is required")
	@Enumerated(EnumType.STRING)
	private final PaymentStatus paymentStatus;

	@NotNull(message = "User id must be present")
	private final Integer userId;

	public PaymentDTO(Double amount, PaymentMethod paymentMethod, PaymentTransactionType transactionType,
			PaymentStatus paymentStatus, Integer userId) {
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.transactionType = transactionType;
		this.paymentStatus = paymentStatus;
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public PaymentTransactionType getTransactionType() {
		return transactionType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public Integer getUserId() {
		return userId;
	}

}
