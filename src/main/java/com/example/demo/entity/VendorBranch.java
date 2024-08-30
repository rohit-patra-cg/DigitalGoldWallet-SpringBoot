package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "vendor_branches")
public class VendorBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;

	@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be positive")
	private Double quantity = 0.0;

	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	// Getters and Setters

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
