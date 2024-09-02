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
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.TransactionTypeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.service.TransactionHistoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transaction_history")
public class TransactionHistoryController {

	@Autowired
	private TransactionHistoryService transactionHistoryService;

	@GetMapping
	ResponseEntity<List<TransactionHistory>> getAllTransactionHistory() {
		return ResponseEntity.ok(transactionHistoryService.getAllTransactionHistory());
	}

	@GetMapping("/{transaction_id}")
	ResponseEntity<TransactionHistory> getTransactionHistoryByTransactionHistoryId(@PathVariable("transaction_id") int transactionhistoryId) throws TransactionHistoryNotFoundException {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionHistoryId(transactionhistoryId));
	}

	@GetMapping("/by_user/{user_id}")
	ResponseEntity<List<TransactionHistory>> getTransactionHistoryByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByUserId(userId));
	}
	
	@GetMapping("/successful")
	ResponseEntity<List<TransactionHistory>> getAllSuccessfulTransactionHistories() {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionStatus(TransactionStatus.SUCCESS));
	}
	
	@GetMapping("/failed")
	ResponseEntity<List<TransactionHistory>> getAllFailedTransactionHistories() {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionStatus(TransactionStatus.FAILED));
	}
	
	@GetMapping("/by_type/{transaction_type}")
	ResponseEntity<List<TransactionHistory>> getAllTransactionHistoriesByTransactionType(@PathVariable("transaction_type") TxnHistoryTransactionType transactionType) throws UserNotFoundException {
		return ResponseEntity.ok(transactionHistoryService.getTransactionHistoryByTransactionType(transactionType));
	}
	
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody TransactionHistoryDTO transactionDto) throws TransactionTypeNotFoundException, UserNotFoundException, VendorBranchNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionHistoryService.createTransaction(transactionDto));
	}
}
