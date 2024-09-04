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

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.exception.InvalidAmountException;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

	private PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
    
	/**
	 * Handles HTTP GET requests to retrieve all payments
	 * @return ResponseEntity containing a list of Payment objects and an HTTP status code(200)
	 */
	@GetMapping
	ResponseEntity<List<Payment>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	/**
	 * Handles HTTP GET requests to retrieve a specific payment by its ID
	 * @param paymentId
	 * @return ResponseEntity containing the Payment object and an HTTP status code(200)
	 * @throws PaymentNotFoundException
	 */
	@GetMapping("/{payment_id}")
	ResponseEntity<Payment> getPaymentById(@PathVariable("payment_id") int paymentId) throws PaymentNotFoundException {
		return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all payments associated with a specific user.
	 * @param userId
	 * @return ResponseEntity containing a list of Payment objects and an HTTP status code(200)
	 * @throws PaymentNotFoundException
	 */
	@GetMapping("/by_user/{user_id}")
	ResponseEntity<Payment> getAllPaymentsByUserId(@PathVariable("user_id") int userId) throws PaymentNotFoundException {
		return ResponseEntity.ok(paymentService.getPaymentById(userId));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all payments with a status of SUCCESS
	 * @return ResponseEntity containing a list of successful Payment objects and an HTTP status code(200)
	 */
	@GetMapping("/successful")
	ResponseEntity<List<Payment>> getAllSuccessfulPayments() {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentStatus(PaymentStatus.SUCCESS));
	}
	
	/**
	 * Handles HTTP GET requests to retrieve all payments with a status of FAILED
	 * @return ResponseEntity containing a list of failed Payment objects and an HTTP status code(200)
	 */
	@GetMapping("/failed")
	ResponseEntity<List<Payment>> getAllFailedPayments() {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentStatus(PaymentStatus.FAILED));
	}
     
	/**
	 * Handles HTTP GET requests to retrieve all payments filtered by the specified payment method.
	 * @param paymentMethod
	 * @return ResponseEntity containing a list of Payment objects and an HTTP status code(200)
	 */
	@GetMapping("/by_payment_method/{payment_method}")
	ResponseEntity<List<Payment>> getAllFailedPayments(@PathVariable("payment_method") PaymentMethod paymentMethod) {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentMethod(paymentMethod));
	}
	
	/**
	 * Handles HTTP POST requests to create a new payment
	 * @param paymentDTO
	 * @return ResponseEntity containing a SuccessResponse object and an HTTP status code(201)
	 * @throws UserNotFoundException
	 * @throws InvalidAmountException
	 */
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) throws UserNotFoundException, InvalidAmountException {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(paymentDTO));
	}
}
