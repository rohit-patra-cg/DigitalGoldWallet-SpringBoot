package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class VendorBranchDTO {

	@NotNull(message = "Vendor Id is Required")
	private final Integer vendorId;
	
	@NotNull(message = "Address Id is Required")
	private final Integer addressId;
	
	@NotNull(message = "Quantity is Required")
	@DecimalMin(value = "0.0", message = "Quantity must be equal to or greater than 0")
	private final Double quantity;

	public VendorBranchDTO(Integer vendorId, Integer addressId, Double quantity) {
		this.vendorId = vendorId;
		this.addressId = addressId;
		this.quantity = quantity;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public Double getQuantity() {
		return quantity;
	}

}
