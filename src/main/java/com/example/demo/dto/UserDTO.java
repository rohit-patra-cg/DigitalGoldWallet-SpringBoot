package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is mandatory")
	@Size(max = 100, message = "Email can't be longer than 100 characters")
	private final String email;

	@NotBlank(message = "Name is mandatory")
	@Size(max = 100, message = "Name can't be longer than 100 characters")
	private final String name;

	@Min(value = 0, message = "Balance must be positive")
	private final Double balance;

	@NotNull(message = "Address id must be present")
	private final Integer addressId;

	/**
	 * Minimum eight characters, at least one upper-case letter, at least one
	 * lower-case letter, one number and at least one special character
	 */
	@NotBlank(message = "Password is required")
	@Pattern(regexp = "(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@$!#%*?&]{8,}$", message = "Password is invalid")
	@Size(max = 32, min = 8, message = "Password size must be between 8 and 32")
	private final String password;

	public UserDTO(String email, String name, Double balance, Integer addressId, String password) {
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.addressId = addressId;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Double getBalance() {
		return balance;
	}

	public int getAddressId() {
		return addressId;
	}

	public String getPassword() {
		return password;
	}

}
