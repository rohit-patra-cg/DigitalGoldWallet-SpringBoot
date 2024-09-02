package com.example.demo.exception;

public class TransactionHistoryNotFoundException extends ResourceNotFoundException {
	
	private static final long serialVersionUID = 2365010332651225706L;

	public TransactionHistoryNotFoundException(String message) {
		super(message);
	}
}
