package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
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

	@Override
	public List<Payment> getAllPaymentsByPaymentStatus(PaymentStatus paymentStatus) {
		return paymentRepository.findAllByPaymentStatus(paymentStatus);
	}

	@Override
	public List<Payment> getAllPaymentsByPaymentMethod(PaymentMethod paymentMethod) {
		return paymentRepository.findAllByPaymentMethod(paymentMethod);
	}

	@Override
	public SuccessResponse createPayment(PaymentDTO paymentDTO) throws UserNotFoundException {
		// TODO: Add money to virtual gold holding if CREDIT and vice versa 
		User user = userService.getUserByUserId(paymentDTO.getUserId());
		Payment payment = new Payment();
		payment.setPaymentMethod(paymentDTO.getPaymentMethod());
		payment.setPaymentStatus(paymentDTO.getPaymentStatus());
		payment.setAmount(paymentDTO.getAmount());
		payment.setTransactionType(paymentDTO.getTransactionType());
		payment.setUser(user);
		paymentRepository.save(payment);
		return new SuccessResponse(new Date(), "Payment was successful");
	}
}
