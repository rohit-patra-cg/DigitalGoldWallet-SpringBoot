package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	ResponseEntity<List<Address>> getAllAddresses() {
		return ResponseEntity.ok(addressService.getAllAddresses());
	}
	
	@GetMapping("/{address_id}")
	ResponseEntity<Address> getAddressByAddressId(@PathVariable("address_id") int addressId) throws AddressNotFoundException {
		return ResponseEntity.ok(addressService.getAddressByAddressId(addressId));
	}
	
	
}
