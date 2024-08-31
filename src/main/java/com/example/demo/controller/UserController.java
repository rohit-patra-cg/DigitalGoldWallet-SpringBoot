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

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.InvalidBalanceException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{user_id}")
	ResponseEntity<User> getUserByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUserId(userId));
	}

	@GetMapping("/name/{user_name}")
	ResponseEntity<User> getUserByUserName(@PathVariable("user_name") String username) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/by_city/{city}")
	ResponseEntity<List<User>> getUserByUserCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(userService.getAllUsersByCity(city));
	}

	@GetMapping("/by_state/{state}")
	ResponseEntity<List<User>> getUserByUserState(@PathVariable("state") String state) {
		return ResponseEntity.ok(userService.getAllUsersByState(state));
	}

	@GetMapping("/check_balance/{user_id}")
	ResponseEntity<Double> getUserBalanceByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserBalanceByUserId(userId));
	}
	
	@GetMapping("/{user_id}/virtual_gold_holdings")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllVirtualGoldHoldingsByUserId(userId));
	}
	
	@GetMapping("/{user_id}/physical_gold_holdings")
	ResponseEntity<List<PhysicalGoldTransaction>> getAllPhysicalGoldHoldingsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllPhysicalGoldTransactionsByUserId(userId));
	}

	@GetMapping("/{user_id}/transaction_history")
	ResponseEntity<List<TransactionHistory>> getAllTransactionsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllTransactionsByUserId(userId));
	}
	
	@GetMapping("/{user_id}/payments")
	ResponseEntity<List<Payment>> getAllPaymentsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllPaymentsByUserId(userId));
	}
	
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody UserDTO userDto) throws UserAlreadyExistsException, AddressNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	
	@PutMapping("/update/{user_id}")
	ResponseEntity<SuccessResponse> updateUser(@PathVariable("user_id") int userId, @Valid @RequestBody UserDTO userDto) throws AddressNotFoundException, UserNotFoundException {
		return ResponseEntity.ok(userService.updateUser(userId, userDto));
	}
	
	@PutMapping("/{user_id}/update_balance/{amount}")
	ResponseEntity<SuccessResponse> updateUserBalance(@PathVariable("user_id") int userId, @PathVariable("amount") double amount) throws UserNotFoundException, InvalidBalanceException {
		if (amount < 0)
			throw new InvalidBalanceException("Amount must be equal to or higher than 0");
		return ResponseEntity.ok(userService.updateUserBalance(userId, amount));
	}
	
	@PutMapping("{user_id}/update_address/{address_id}")
	ResponseEntity<SuccessResponse> updateUserAddress(@PathVariable("user_id") int userId, @PathVariable("address_id") int addressId) throws UserNotFoundException, AddressNotFoundException {
		return ResponseEntity.ok(userService.updateUserAddress(userId, addressId));
	}
}
