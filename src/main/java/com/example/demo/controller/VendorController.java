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
import com.example.demo.entity.Vendor;
import com.example.demo.exception.InvalidGoldPriceException;
import com.example.demo.exception.InvalidGoldQuantityException;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.service.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

	private VendorService vendorService;

	public VendorController(VendorService vendorService) {
		this.vendorService = vendorService;
	}

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

	@PutMapping("/update/{vendor_id}")
	public ResponseEntity<SuccessResponse> updateVendor(@PathVariable("vendor_id") int vendorId, @Valid @RequestBody Vendor vendor) throws VendorNotFoundException {
		return ResponseEntity.ok(vendorService.updateVendor(vendorId, vendor));
	}

	@PutMapping("/{vendor_id}/total_gold_quantity/{quantity}")
	public ResponseEntity<SuccessResponse> updateTotalGoldQuantity(@PathVariable("vendor_id") int vendorId,@PathVariable("quantity") Double quantity) throws VendorNotFoundException, InvalidGoldQuantityException {
		if (quantity < 0)
			throw new InvalidGoldQuantityException("Quantity must be equal to or higher than 0");
		return ResponseEntity.ok(vendorService.updateTotalGoldQuantity(vendorId, quantity));
	}
	
	@PutMapping("/{vendor_id}/new_current_gold_price/{new_price}")
    public ResponseEntity<SuccessResponse> updateCurrentGoldPrice(@PathVariable("vendor_id") int vendorId, @PathVariable("new_price") Double newPrice) throws VendorNotFoundException,InvalidGoldPriceException{
		if (newPrice < 0)
			throw new InvalidGoldPriceException("Price must be equal to or higher than 0");
		return ResponseEntity.ok(vendorService.updateCurrentGoldPrice(vendorId, newPrice));
    }

}
