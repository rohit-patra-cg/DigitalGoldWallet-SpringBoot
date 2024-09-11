package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.service.TransactionHistoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transaction_history")
public class TransactionHistoryController {

	private TransactionHistoryService transactionHistoryService;
	
	public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
		this.transactionHistoryService = transactionHistoryService;
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records
	 * @return ResponseEntity containing a list of TransactionHistory objects and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<TransactionHistory>> getAllTransactionHistory() {
		return ResponseEntity.ok(transactionHistoryService.getAllTransactionHistory());
	}
    
	/**
	 * Handles HTTP GET requests to retrieve a specific transaction history record by its ID
	 * @param transactionhistoryId
	 * @return ResponseEntity containing the TransactionHistory object and an HTTP status code(200)
	 * @throws TransactionHistoryNotFoundException
	 */
	@GetMapping("/{transaction_id}")
	ResponseEntity<TransactionHistory> getTransactionHistoryByTransactionHistoryId(@PathVariable("transaction_id") int transactionhistoryId) throws TransactionHistoryNotFoundException {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionHistoryId(transactionhistoryId));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records associated with a specific user
	 * @param userId
	 * @return ResponseEntity containing a list of TransactionHistory objects and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
	@GetMapping("/by_user/{user_id}")
	ResponseEntity<List<TransactionHistory>> getTransactionHistoryByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByUserId(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records with a status of SUCCESS
	 * @return ResponseEntity containing a list of successful TransactionHistory objects and an HTTP status code(200)
	 */
	@GetMapping("/successful")
	ResponseEntity<List<TransactionHistory>> getAllSuccessfulTransactionHistories() {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionStatus(TransactionStatus.SUCCESS));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records with a status of FAILED
	 * @return ResponseEntity containing a list of successful TransactionHistory objects and an HTTP status code(200)
	 */
	@GetMapping("/failed")
	ResponseEntity<List<TransactionHistory>> getAllFailedTransactionHistories() {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionStatus(TransactionStatus.FAILED));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all transaction history records of a specific type
	 * @param transactionType
	 * @return ResponseEntity containing a list of TransactionHistory objects and an HTTP status code(200)
	 */
	@GetMapping("/by_type/{transaction_type}")
	ResponseEntity<List<TransactionHistory>> getAllTransactionHistoriesByTransactionType(@PathVariable("transaction_type") TxnHistoryTransactionType transactionType) {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionType(transactionType));
	}
	
	/**
	 * Handles HTTP POST requests to create a new transaction history entry
	 * @param transactionDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createTransaction(@Valid @RequestBody TransactionHistoryDTO transactionDto) throws UserNotFoundException, VendorBranchNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionHistoryService.createTransaction(transactionDto));
	}
}
