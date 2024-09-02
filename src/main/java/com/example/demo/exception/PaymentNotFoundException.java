package com.example.demo.exception;

public class PaymentNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(String message) {
		super(message);
	}
}
