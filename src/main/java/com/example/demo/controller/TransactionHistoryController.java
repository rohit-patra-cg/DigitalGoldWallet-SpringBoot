package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.TransactionHistoryService;

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

}
