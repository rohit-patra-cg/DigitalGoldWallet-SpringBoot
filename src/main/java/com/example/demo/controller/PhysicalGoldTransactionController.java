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

	private PhysicalGoldTransactionService physicalGoldTransactionService;
		
	public PhysicalGoldTransactionController(PhysicalGoldTransactionService physicalGoldTransactionService) {
		this.physicalGoldTransactionService = physicalGoldTransactionService;
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all physical gold transactions
	 * @return ResponseEntity containing a list of PhysicalGoldTransaction objects and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<PhysicalGoldTransaction>> getAllPhysicalGoldTransactions() {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactions());
	}
	
	/**
	 * Handles HTTP GET requests to retrieve a specific physical gold transaction by its ID
	 * @param transactionId
	 * @return ResponseEntity containing the PhysicalGoldTransaction object and an HTTP status code(200)
	 * @throws PhysicalGoldTransactionNotFoundException
	 */
	@GetMapping("/{transaction_id}")
	ResponseEntity<PhysicalGoldTransaction> getPhysicalGoldTransactionById(@PathVariable("transaction_id") int transactionId) throws PhysicalGoldTransactionNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getPhysicalGoldTransactionById(transactionId));
	}
	
	/**
	 *  Handles HTTP GET requests to retrieve all physical gold transactions associated with a specific user
	 * @param userId
	 * @return ResponseEntity containing a list of PhysicalGoldTransaction objects and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
	@GetMapping("/byUser/{user_id}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByUserId(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all physical gold transactions associated with a specific branch.
	 * @param branchId
	 * @return ResponseEntity containing a list of PhysicalGoldTransaction objects and an HTTP status code(200)
	 * @throws VendorBranchNotFoundException
	 */
	@GetMapping("/by_branch/{branch_id}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByBranchId(@PathVariable("branch_id") int branchId) throws VendorBranchNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByBranchId(branchId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all physical gold transactions delivered to a specific city 
	 * @param city
	 * @return ResponseEntity containing a list of PhysicalGoldTransaction objects and an HTTP status code(200)
	 */
	@GetMapping("/by_delivery_city/{city}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByDeliveryCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByDeliveryCity(city));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all physical gold transactions delivered to a specific state
	 * @param state
	 * @return ResponseEntity containing a list of PhysicalGoldTransaction objects and an HTTP status code(200)
	 */
	@GetMapping("/by_delivery_state/{state}")
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByDeliveryState(@PathVariable("state") String state) {
		return ResponseEntity.ok(physicalGoldTransactionService.getAllPhysicalGoldTransactionsByDeliveryState(state));
	}
	
	/**
	 * Handles HTTP POST requests to create a new physical gold transaction
	 * @param transactionDTO
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createPhysicalGoldTransaction(@Valid @RequestBody PhysicalGoldTransactionDTO transactionDTO) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(physicalGoldTransactionService.createPhysicalGoldTransaction(transactionDTO));
	}
	
	/**
	 * Handles HTTP PUT requests to update an existing physical gold transaction
	 * @param transactionId
	 * @param transactionDTO
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException
	 * @throws VendorBranchNotFoundException
	 * @throws PhysicalGoldTransactionNotFoundException
	 */
	@PutMapping("/update/{transaction_id}")
	ResponseEntity<SuccessResponse> createPhysicalGoldTransaction(@PathVariable("transaction_id") int transactionId, @Valid @RequestBody PhysicalGoldTransactionDTO transactionDTO) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException, PhysicalGoldTransactionNotFoundException {
		return ResponseEntity.ok(physicalGoldTransactionService.updatePhysicalGoldTransaction(transactionId, transactionDTO));
	}
}
