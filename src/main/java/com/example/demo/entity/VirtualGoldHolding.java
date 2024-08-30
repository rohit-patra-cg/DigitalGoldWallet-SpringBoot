package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "virtual_gold_holdings")
public class VirtualGoldHolding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holdingId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	private VendorBranch branch;

	@DecimalMin(value = "0.0", inclusive = true, message = "Quantity must be positive")
	private Double quantity;

	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	// Getters and Setters

	public int getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(int holdingId) {
		this.holdingId = holdingId;
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
