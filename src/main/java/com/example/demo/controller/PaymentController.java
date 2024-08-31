package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payment;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
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
}
