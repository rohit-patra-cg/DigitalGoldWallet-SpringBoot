package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface PaymentService {
	List<Payment> getAllPayments();
	Payment getPaymentById(int paymentId) throws PaymentNotFoundException;
	List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException;
	List<Payment> getAllPaymentsByPaymentStatus(PaymentStatus paymentStatus);
	List<Payment> getAllPaymentsByPaymentMethod(PaymentMethod paymentMethod);
	SuccessResponse createPayment(PaymentDTO paymentDTO) throws UserNotFoundException;
}