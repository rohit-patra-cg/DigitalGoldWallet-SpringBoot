package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.service.PhysicalGoldTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/physical_gold_transactions")
public class PhysicalGoldTransactionController {

	@Autowired
	private PhysicalGoldTransactionService physicalGoldTransactionService;
	
	@GetMapping
	ResponseEntity<List<PhysicalGoldTransaction>> getAllPhysicalGoldTransactions() {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactions());
	}
	
	@GetMapping("/{transaction_id}")
	ResponseEntity<PhysicalGoldTransaction> getPhysicalGoldTransactionById(@PathVariable("transaction_id") int transactionId) throws PhysicalGoldTransactionNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getPhysicalGoldTransactionById(transactionId));
	}
	
	@GetMapping("/byUser/{user_id}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByUserId(userId));
	}
	
	@GetMapping("/by_branch/{branch_id}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByBranchId(@PathVariable("branch_id") int branchId) throws VendorBranchNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByBranchId(branchId));
	}
	
	@GetMapping("/by_delivery_city/{city}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByDeliveryCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByDeliveryCity(city));
	}
	
	@GetMapping("/by_delivery_state/{state}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByDeliveryState(@PathVariable("state") String state) {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByDeliveryState(state));
	}
	
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createPhysicalGoldTransaction(@Valid @RequestBody PhysicalGoldTransactionDTO transactionDTO) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(physicalGoldTransactionService.createPhysicalGoldTransaction(transactionDTO));
	}
	
	@PutMapping("/update/{transaction_id}")
	ResponseEntity<SuccessResponse> createPhysicalGoldTransaction(@PathVariable("transaction_id") int transactionId, @Valid @RequestBody PhysicalGoldTransactionDTO transactionDTO) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException, PhysicalGoldTransactionNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.updatePhysicalGoldTransaction(transactionId, transactionDTO));
	}
}
