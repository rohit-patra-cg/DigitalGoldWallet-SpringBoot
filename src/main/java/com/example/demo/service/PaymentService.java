package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Payment;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.exception.InvalidAmountException;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface PaymentService {
	/**
	 * Get All Payments
	 * @return List<Payment> Collection of Payments
	 */
	List<Payment> getAllPayments();
	
	/**
	 * Get Payment by payment_id
	 * @param paymentId
	 * @return Payment Object 
	 * @throws PaymentNotFoundException
	 */
	Payment getPaymentById(int paymentId) throws PaymentNotFoundException;
	
	/**
	 * Get All Payments by user_id
	 * @param userId
	 * @return List<Payment> Collection of Payments 
	 * @throws UserNotFoundException
	 */
	List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get All Payments by PaymentStatus
	 * @param paymentStatus
	 * @return List<Payment> Collection of Payments  
	 */
	List<Payment> getAllPaymentsByPaymentStatus(PaymentStatus paymentStatus);
	
	/**
	 * Get All Payments by PaymentMethod
	 * @param paymentMethod
	 * @return List<Payment> Collection of Payments  
	 */
	List<Payment> getAllPaymentsByPaymentMethod(PaymentMethod paymentMethod);
	
	/**
	 * Add New Payment
	 * @param paymentDTO
	 * @return SuccessResponse Response for successfully adding a new payment
	 * @throws UserNotFoundException
	 * @throws InvalidAmountException
	 */
	SuccessResponse createPayment(PaymentDTO paymentDTO) throws UserNotFoundException, InvalidAmountException;
}
