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

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all users
	 * @return ResponseEntity containing a list of users and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	/**
	 * Handles HTTP GET requests to retrieve a user by their ID
	 * @param userId
	 * @return ResponseEntity containing the users and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
    @GetMapping("/{user_id}")
	ResponseEntity<User> getUserByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUserId(userId));
	}
    
    /**
     * Handles HTTP GET requests to retrieve a user By user_name
     * @param user_name
     * @return ResponseEntity containing the users and an HTTP status code(200)
     * @throws UserNotFoundException
     */
	@GetMapping("/name/{user_name}")
	ResponseEntity<User> getUserByUserName(@PathVariable("user_name") String username) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	/**
	 * Handles HTTP requests to retrieve users by their city 
	 * @param city
	 * @return ResponseEntity containing the users and an HTTP status code(200)
	 */
    @GetMapping("/by_city/{city}")
	ResponseEntity<List<User>> getUserByUserCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(userService.getAllUsersByCity(city));
	}
    
    /**
     * Handles HTTP requests to retrieve users by their state  
     * @param state
     * @return ResponseEntity containing the users and an HTTP status code(200)
     */
    @GetMapping("/by_state/{state}")
	ResponseEntity<List<User>> getUserByUserState(@PathVariable("state") String state) {
		return ResponseEntity.ok(userService.getAllUsersByState(state));
	}
    
	/**
	 * Handles HTTP requests to retrieve the balance of the user by user_id
	 * @param userId
	 * @return ResponseEntity containing the user's balance and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
    @GetMapping("/check_balance/{user_id}")
	ResponseEntity<Double> getUserBalanceByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserBalanceByUserId(userId));
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all virtual gold holdings for a specific user by their user ID.
	 * @param userId
	 * @return ResponseEntity containing a list of virtual gold holdings and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
	@GetMapping("/{user_id}/virtual_gold_holdings")
	ResponseEntity<List<VirtualGoldHolding>> getAllVirtualGoldHoldingsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllVirtualGoldHoldingsByUserId(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all Physical gold holdings for a specific user by their user Id
	 * @param userId
	 * @return ResponseEntity containing a list of physical gold holdings and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
	@GetMapping("/{user_id}/physical_gold_holdings")
	ResponseEntity<List<PhysicalGoldTransaction>> getAllPhysicalGoldHoldingsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllPhysicalGoldTransactionsByUserId(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve the transaction history of a user by their user ID
	 * @param userId
	 * @return ResponseEntity containing a list of transaction history records and an HTTP status code(200)
	 * @throws UserNotFoundException
	 */
    @GetMapping("/{user_id}/transaction_history")
	ResponseEntity<List<TransactionHistory>> getAllTransactionsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllTransactionsByUserId(userId));
	}
    
    /**
     * Handles HTTP GET requests to retrieve all payments made by a specific user, identified by their user ID
     * @param userId
     * @return esponseEntity containing a list of payment records and an HTTP status code(200)
     * @throws UserNotFoundException
     */
	@GetMapping("/{user_id}/payments")
	ResponseEntity<List<Payment>> getAllPaymentsByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getAllPaymentsByUserId(userId));
	}
	
	/**
	 * Handles HTTP POST requests to create a new user.
	 * @param userDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws UserAlreadyExistsException
	 * @throws AddressNotFoundException
	 */
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody UserDTO userDto) throws UserAlreadyExistsException, AddressNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	
	/**
	 * Handles HTTP PUT requests to update an existing user's details.
	 * @param userId
	 * @param userDto
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws AddressNotFoundException
	 * @throws UserNotFoundException
	 */
	@PutMapping("/update/{user_id}")
	ResponseEntity<SuccessResponse> updateUser(@PathVariable("user_id") int userId, @Valid @RequestBody UserDTO userDto) throws AddressNotFoundException, UserNotFoundException {
		return ResponseEntity.ok(userService.updateUser(userId, userDto));
	}
	
	/**
	 * Handles HTTP PUT requests to update a user's balance.
	 * @param userId
	 * @param amount
	 * @return  ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws UserNotFoundException
	 * @throws InvalidBalanceException
	 */
	@PutMapping("/{user_id}/update_balance/{amount}")
	ResponseEntity<SuccessResponse> updateUserBalance(@PathVariable("user_id") int userId, @PathVariable("amount") double amount) throws UserNotFoundException, InvalidBalanceException {
		if (amount < 0)
			throw new InvalidBalanceException("Amount must be equal to or higher than 0");
		return ResponseEntity.ok(userService.updateUserBalance(userId, amount));
	}
	
	/**
	 * Handles HTTP PUT requests to update the address of a specific user
	 * @param userId
	 * @param addressId
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(200)
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException
	 */
	@PutMapping("{user_id}/update_address/{address_id}")
	ResponseEntity<SuccessResponse> updateUserAddress(@PathVariable("user_id") int userId, @PathVariable("address_id") int addressId) throws UserNotFoundException, AddressNotFoundException {
		return ResponseEntity.ok(userService.updateUserAddress(userId, addressId));
	}
}
