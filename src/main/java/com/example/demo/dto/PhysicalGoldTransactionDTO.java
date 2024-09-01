package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class PhysicalGoldTransactionDTO {
	@NotNull(message = "User ID is required")
	private final Integer userId;

	@NotNull(message = "Vendor Branch ID is required")
	private final Integer branchId;

	@DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be positive")
	@NotNull(message = "Quantity is required")
	private final Double quantity;

	@NotNull(message = "Address ID is required")
	private final Integer addressId;

	public PhysicalGoldTransactionDTO(Integer userId, Integer branchId, Double quantity, Integer addressId) {
		this.userId = userId;
		this.branchId = branchId;
		this.quantity = quantity;
		this.addressId = addressId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Integer getAddressId() {
		return addressId;
	}

}
