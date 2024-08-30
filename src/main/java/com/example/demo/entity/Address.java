package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@NotBlank(message = "Street is mandatory")
	@Size(max = 255, message = "Street can't be longer than 255 characters")
	private String street;

	@NotBlank(message = "City is mandatory")
	@Size(max = 100, message = "City can't be longer than 100 characters")
	private String city;

	@NotBlank(message = "State is mandatory")
	@Size(max = 100, message = "State can't be longer than 100 characters")
	private String state;

	@Size(max = 20, message = "Postal code can't be longer than 20 characters")
	private String postalCode;

	@NotBlank(message = "Country is mandatory")
	@Size(max = 100, message = "Country can't be longer than 100 characters")
	private String country;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
