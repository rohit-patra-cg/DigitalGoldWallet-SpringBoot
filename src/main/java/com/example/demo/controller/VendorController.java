package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.service.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@GetMapping
	public ResponseEntity<List<Vendor>> getAllVendors() {
		return ResponseEntity.ok(vendorService.getAllVendors());
	}

	@GetMapping("/{vendor_id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable("vendor_id") int id) throws VendorNotFoundException {
		return ResponseEntity.ok(vendorService.getVendorById(id));
	}

	@GetMapping("/name/{vendor_name}")
	public ResponseEntity<Vendor> getVendorByName(@PathVariable("vendor_name") String name)
			throws VendorNotFoundException {
		return ResponseEntity.ok(vendorService.getVendorByName(name));
	}

	@PostMapping("/add")
	ResponseEntity<SuccessResponse> addVendor(@Valid @RequestBody Vendor vendor) throws VendorAlreadyExistsException {
		return ResponseEntity.status(HttpStatus.CREATED).body(vendorService.addVendor(vendor));
	}

}
