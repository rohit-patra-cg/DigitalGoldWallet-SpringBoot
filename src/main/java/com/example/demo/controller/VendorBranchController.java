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
import com.example.demo.dto.VendorBranchDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.InvalidGoldQuantityException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.service.VendorBranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vendor_branches")
public class VendorBranchController {

	private VendorBranchService vendorBranchService;

	public VendorBranchController(VendorBranchService vendorBranchService) {
		this.vendorBranchService = vendorBranchService;
	}
    
	
	/**
	 * Handles HTTP GET requests to retrieve all vendor branches
	 * @return ResponseEntity containing a list of VendorBranch objects and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<VendorBranch>> getAllVendorBranches() {
		return ResponseEntity.ok(vendorBranchService.getAllVendorBranches());
	}
    
	/**
	 * Handles HTTP GET requests to retrieve a specific vendor branch by its ID.
	 * @param branchId
	 * @return  ResponseEntity containing the VendorBranch object and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 */
	@GetMapping("/{branch_id}")
	ResponseEntity<VendorBranch> getVendorBranchById(@PathVariable("branch_id") int branchId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByBranchId(branchId));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all vendor branches associated with a specific vendor
	 * @param vendorId
	 * @return ResponseEntity containing a list of VendorBranch objects and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 */
	@GetMapping("/by_vendor/{vendor_id}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByVendorId(@PathVariable("vendor_id") int vendorId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByVendorId(vendorId));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all vendor branches located in a specific city
	 * @param city
	 * @return ResponseEntity containing a list of VendorBranch objects and an HTTP status code(200)
	 */
	@GetMapping("/by_city/{city}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByCity(city));
	}
   
	/**
	 * Handles HTTP GET requests to retrieve all vendor branches located in a specific statE
	 * @param state
	 * @return  ResponseEntity containing a list of VendorBranch objects and an HTTP status code(200)
	 */
	@GetMapping("/by_state/{state}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByState(@PathVariable("state") String state) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByState(state));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all vendor branches located in a specific country
	 * @param country
	 * @return ResponseEntity containing a list of VendorBranch objects and an HTTP status code(200)
	 */
	@GetMapping("/by_country/{country}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByCountry(@PathVariable("country") String country) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByCountry(country));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records associated with a specific vendor branch
	 * @param branchId
	 * @return ResponseEntity containing a list of TransactionHistory objects and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 */
	@GetMapping("/transactions/{branch_id}")
	ResponseEntity<List<TransactionHistory>> getVendorBranchTransactions(@PathVariable("branch_id") int branchId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchTransactionsByBranchId(branchId));
	}
    
	/**
	 * Handles HTTP POST requests to add a new vendor branch
	 * @param vendorBranchDTO
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws VendorNotFoundException
	 * @throws AddressNotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addVendorBranch(@Valid @RequestBody VendorBranchDTO vendorBranchDTO)
			throws VendorNotFoundException, AddressNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(vendorBranchService.addVendorBranch(vendorBranchDTO));
	}
    
	/**
	 * Handles HTTP POST requests to transfer a specified quantity of gold between two vendor branches
	 * @param sourceBranchId
	 * @param destinationBranchId
	 * @param quantity
	 * @retur ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 * @throws InvalidGoldQuantityException
	 */
	@PostMapping("/transfer/{source_branch_id}/{destination_branch_id}/{quantity}")
	public ResponseEntity<SuccessResponse> transferGoldBetweenBranches(@PathVariable("source_branch_id") int sourceBranchId, @PathVariable("destination_branch_id") int destinationBranchId, @PathVariable("quantity") double quantity) throws VendorBranchNotFoundException, InvalidGoldQuantityException {
		return ResponseEntity.ok(vendorBranchService.transferGoldBetweenBranches(sourceBranchId, destinationBranchId, quantity));
	}
    
	/**
	 * Handles HTTP PUT requests to update details of a specific vendor branch
	 * @param branchId
	 * @param vendorBranchDTO
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 * @throws AddressNotFoundException
	 * @throws VendorNotFoundException
	 */
	@PutMapping("/update/{branch_id}")
	public ResponseEntity<SuccessResponse> updateVendorBranch(@PathVariable("branch_id") int branchId, @RequestBody VendorBranchDTO vendorBranchDTO) throws VendorBranchNotFoundException, AddressNotFoundException, VendorNotFoundException {
		return ResponseEntity.ok(vendorBranchService.updateVendorBranch(branchId, vendorBranchDTO));
	}
}
