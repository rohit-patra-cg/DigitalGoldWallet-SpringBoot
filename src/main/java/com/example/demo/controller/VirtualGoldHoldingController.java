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
import com.example.demo.exception.InvalidGoldQuantityException;
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
    
	/**
	 * Handles HTTP GET requests to retrieve a list of all virtual gold holdings
	 * @return ResponseEntity containing a list of VirtualGoldHolding objects and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldings() {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldings());
	}
	
	/**
	 * Handles HTTP GET requests to retrieve a specific virtual gold holding by its ID
	 * @param holdingId
	 * @return ResponseEntity containing the VirtualGoldHolding object and an HTTP status code(200)
	 * @throws VirtualGoldHoldingNotFoundException
	 */
	@GetMapping("/{holding_id}")
	ResponseEntity<VirtualGoldHolding> getAllVirtualGoldHoldings(@PathVariable("holding_id") int holdingId) throws VirtualGoldHoldingNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getVirtualGoldHoldingById(holdingId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all virtual gold holdings for a specific user.
	 * @param userId
	 * @returnResponseEntity containing a list of VirtualGoldHolding objects and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
	@GetMapping("/users/{users_id}")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserId(@PathVariable("users_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldingsByUserId(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all virtual gold holdings for a specific user and vendor
	 * @param userId
	 * @param vendorId
	 * @return  ResponseEntity containing a list of VirtualGoldHolding objects and an HTTP status code(200)
	 * @throws UserNotFoundException
	 * @throws VendorNotFoundException
	 */
	@GetMapping("/byUserAndVendor/{user_id}/{vendor_id}")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserIdAndVendorId(@PathVariable("user_id") int userId, @PathVariable("vendor_id") int vendorId) throws UserNotFoundException, VendorNotFoundException {
		return ResponseEntity.ok(virtualGoldHoldingService.getAllVirtualGoldHoldingsByUserIdAndVendorId(userId, vendorId));
	}
	
	/**
	 * Handles HTTP POST requests to add a new virtual gold holding
	 * @param holdingDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws VirtualGoldHoldingAreadyExistsException
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> addVirtualGoldHolding(@Valid @RequestBody VirtualGoldHoldingDTO holdingDto) throws VirtualGoldHoldingAreadyExistsException, UserNotFoundException, VendorBranchNotFoundException{
		return ResponseEntity.status(HttpStatus.CREATED).body(virtualGoldHoldingService.addVirtualGoldHolding(holdingDto));
	}
	
	/**
	 * Handles HTTP PUT requests to update an existing virtual gold holding
	 * @param holdingId
	 * @param holdingDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws VirtualGoldHoldingNotFoundException
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	@PutMapping("/update/{holding_id}")
	ResponseEntity<SuccessResponse> updateVirtualGoldHolding(@PathVariable("holding_id") int holdingId, @Valid @RequestBody VirtualGoldHoldingDTO holdingDto) throws VirtualGoldHoldingNotFoundException,UserNotFoundException, VendorBranchNotFoundException{
		return ResponseEntity.ok(virtualGoldHoldingService.updateVirtualGoldHolding(holdingId, holdingDto));
	}
	
	/**
	 * Handles HTTP POST requests to convert a specified quantity of virtual gold holdings to physical gold
	 * @param quantity
	 * @param holdingId
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws VirtualGoldHoldingNotFoundException
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 * @throws InvalidGoldQuantityException
	 */
	@PostMapping("/convertToPhysical/{holding_id}/{quantity}")
	ResponseEntity<SuccessResponse> convertToPysical(@PathVariable("quantity") double quantity, @PathVariable("holding_id")int holdingId ) throws VirtualGoldHoldingNotFoundException, UserNotFoundException, VendorBranchNotFoundException, InvalidGoldQuantityException{
		return ResponseEntity.ok(virtualGoldHoldingService.convertToPysical(quantity, holdingId));
	}
	
}
