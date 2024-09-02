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

	@GetMapping
	ResponseEntity<List<VendorBranch>> getAllVendorBranches() {
		return ResponseEntity.ok(vendorBranchService.getAllVendorBranches());
	}

	@GetMapping("/{branch_id}")
	ResponseEntity<VendorBranch> getVendorBranchById(@PathVariable("branch_id") int branchId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByBranchId(branchId));
	}

	@GetMapping("/by_vendor/{vendor_id}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByVendorId(@PathVariable("vendor_id") int vendorId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByVendorId(vendorId));
	}

	@GetMapping("/by_city/{city}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByCity(city));
	}

	@GetMapping("/by_state/{state}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByState(@PathVariable("state") String state) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByState(state));
	}

	@GetMapping("/by_country/{country}")
	ResponseEntity<List<VendorBranch>> getVendorBranchesByCountry(@PathVariable("country") String country) {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchByCountry(country));
	}

	@GetMapping("/transactions/{branch_id}")
	ResponseEntity<List<TransactionHistory>> getVendorBranchTransactions(@PathVariable("branch_id") int branchId)
			throws VendorBranchNotFoundException {
		return ResponseEntity.ok(vendorBranchService.getVendorBranchTransactionsByBranchId(branchId));
	}

	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addVendorBranch(@Valid @RequestBody VendorBranchDTO vendorBranchDTO)
			throws VendorNotFoundException, AddressNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(vendorBranchService.addVendorBranch(vendorBranchDTO));
	}

	@PostMapping("/transfer/{source_branch_id}/{destination_branch_id}/{quantity}")
	public ResponseEntity<SuccessResponse> transferGoldBetweenBranches(@PathVariable("source_branch_id") int sourceBranchId, @PathVariable("destination_branch_id") int destinationBranchId, @PathVariable("quantity") double quantity) throws VendorBranchNotFoundException, InvalidGoldQuantityException {
		return ResponseEntity.ok(vendorBranchService.transferGoldBetweenBranches(sourceBranchId, destinationBranchId, quantity));
	}

	@PutMapping("/update/{branch_id}")
	public ResponseEntity<SuccessResponse> updateVendorBranch(@PathVariable("branch_id") int branchId, @RequestBody VendorBranchDTO vendorBranchDTO) throws VendorBranchNotFoundException, AddressNotFoundException, VendorNotFoundException {
		return ResponseEntity.ok(vendorBranchService.updateVendorBranch(branchId, vendorBranchDTO));
	}
}
