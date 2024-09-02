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
import com.example.demo.dto.VirtualGoldHoldingDTO;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.exception.VirtualGoldHoldingAreadyExistsException;
import com.example.demo.exception.VirtualGoldHoldingNotFoundException;
import com.example.demo.service.VirtualGoldHoldingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/virtual_gold_holding")
public class VirtualGoldHoldingController {

	private VirtualGoldHoldingService virtualGoldHoldingService;
	
	public VirtualGoldHoldingController(VirtualGoldHoldingService virtualGoldHoldingService) {
		this.virtualGoldHoldingService = virtualGoldHoldingService;
	}

	@GetMapping
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldings() {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldings());
	}
	
	@GetMapping("/{holding_id}")
	ResponseEntity<VirtualGoldHolding> getAllVirtualGoldHoldings(@PathVariable("holding_id") int holdingId) throws VirtualGoldHoldingNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getVirtualGoldHoldingById(holdingId));
	}
	
	@GetMapping("/users/{users_id}")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserId(@PathVariable("users_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldingsByUserId(userId));
	}
	
	@GetMapping("/byUserAndVendor/{user_id}/{vendor_id}")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserIdAndVendorId(@PathVariable("user_id") int userId, @PathVariable("vendor_id") int vendorId) throws UserNotFoundException, VendorNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldingsByUserIdAndVendorId(userId, vendorId));
	}
	
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> addVirtualGoldHolding(@Valid @RequestBody VirtualGoldHoldingDTO holdingDto) throws VirtualGoldHoldingAreadyExistsException, UserNotFoundException, VendorBranchNotFoundException{
		return ResponseEntity.status(HttpStatus.CREATED).body(virtualGoldHoldingService.addVirtualGoldHolding(holdingDto));
	}
	
	@PutMapping("/update/{holding_id}")
	ResponseEntity<SuccessResponse> updateVirtualGoldHolding(@PathVariable("holding_id") int holdingId, @Valid @RequestBody VirtualGoldHoldingDTO holdingDto) throws VirtualGoldHoldingNotFoundException,UserNotFoundException, VendorBranchNotFoundException{
		return ResponseEntity.ok(virtualGoldHoldingService.updateVirtualGoldHolding(holdingId, holdingDto));
	}
}
