package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class VirtualGoldHoldingDTO {

	@NotNull(message = "User ID is required")
	private final Integer userId;
	
	@NotNull(message = "Branch ID is required")
	private final Integer branchId;
	
	@NotNull(message = "Quantity is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be positive")
	private Double quantity;

	public Integer getUserId() {
		return userId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public VirtualGoldHoldingDTO( Integer userId,Integer branchId, Double quantity) {
		this.userId = userId;
		this.branchId = branchId;
		this.quantity = quantity;
	}

	
	
	
	
}
