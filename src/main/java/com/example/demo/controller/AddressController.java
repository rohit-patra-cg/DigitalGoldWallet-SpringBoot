package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

	private AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	ResponseEntity<List<Address>> getAllAddresses() {
		return ResponseEntity.ok(addressService.getAllAddresses());
	}

	@GetMapping("/{address_id}")
	ResponseEntity<Address> getAddressByAddressId(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
		return ResponseEntity.ok(addressService.getAddressByAddressId(addressId));
	}

	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createAddress(@Valid @RequestBody Address address) {
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(address));
	}
	
	@PutMapping("/update/{address_id}")
	ResponseEntity<SuccessResponse> updateAddress(@PathVariable("address_id") int addressId, @Valid @RequestBody Address address) throws AddressNotFoundException {
		return ResponseEntity.ok(addressService.updateAddress(addressId, address));
	}
}
