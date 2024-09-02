package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	public UserDTO(String email, String name, Double balance, Integer addressId) {
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.addressId = addressId;
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

}
