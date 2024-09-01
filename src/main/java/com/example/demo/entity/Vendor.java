package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vendors")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendorId;

	@NotBlank(message = "Vendor name is mandatory")
	@Size(max = 100, message = "Vendor name can't be longer than 100 characters")
	private String vendorName;

	private String description;

	@Size(max = 100, message = "Contact person name can't be longer than 100 characters")
	private String contactPersonName;

	@Column(unique = true)
	@Email(message = "Contact email should be valid")
	@Size(max = 100, message = "Contact email can't be longer than 100 characters")
	private String contactEmail;

	@Size(max = 20, message = "Contact phone can't be longer than 20 characters")
	private String contactPhone;

	@Size(max = 255, message = "Website URL can't be longer than 255 characters")
	private String websiteUrl;

	@DecimalMin(value = "0.0", inclusive = true, message = "Total gold quantity must be positive")
	@NotNull
	private Double totalGoldQuantity = 0.0;

	@DecimalMin(value = "0.0", inclusive = true, message = "Current gold price must be positive")
	@NotNull
	private Double currentGoldPrice = 5700.0;

	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Double getTotalGoldQuantity() {
		return totalGoldQuantity;
	}

	public void setTotalGoldQuantity(Double totalGoldQuantity) {
		this.totalGoldQuantity = totalGoldQuantity;
	}

	public Double getCurrentGoldPrice() {
		return currentGoldPrice;
	}

	public void setCurrentGoldPrice(Double currentGoldPrice) {
		this.currentGoldPrice = currentGoldPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
