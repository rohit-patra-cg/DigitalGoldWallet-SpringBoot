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

	@GetMapping
	ResponseEntity<List<Payment>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}
	
	@GetMapping("/{payment_id}")
	ResponseEntity<Payment> getPaymentById(@PathVariable("payment_id") int paymentId) throws PaymentNotFoundException {
		return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	}
	
	@GetMapping("/by_user/{user_id}")
	ResponseEntity<Payment> getAllPaymentsByUserId(@PathVariable("user_id") int userId) throws PaymentNotFoundException {
		return ResponseEntity.ok(paymentService.getPaymentById(userId));
	}
	
	@GetMapping("/successful")
	ResponseEntity<List<Payment>> getAllSuccessfulPayments() {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentStatus(PaymentStatus.SUCCESS));
	}
	
	@GetMapping("/failed")
	ResponseEntity<List<Payment>> getAllFailedPayments() {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentStatus(PaymentStatus.FAILED));
	}

	@GetMapping("/by_payment_method/{payment_method}")
	ResponseEntity<List<Payment>> getAllFailedPayments(@PathVariable("payment_method") PaymentMethod paymentMethod) {
		return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentMethod(paymentMethod));
	}
	
	@PostMapping("/add")
	ResponseEntity<SuccessResponse> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(paymentDTO));
	}
}
