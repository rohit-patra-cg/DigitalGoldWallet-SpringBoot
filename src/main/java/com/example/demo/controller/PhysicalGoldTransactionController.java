package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.PhysicalGoldTransactionService;

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
	ResponseEntity<List<PhysicalGoldTransaction>> getPhysicalGoldTransactionByBranchId(@PathVariable("branch_id") int branchId) {
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
}
