package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentById(int paymentId) throws PaymentNotFoundException {
		return paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment#" + paymentId + " not found"));
	}

	@Override
	public List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return paymentRepository.findAllByUserId(userId);
	}

}
