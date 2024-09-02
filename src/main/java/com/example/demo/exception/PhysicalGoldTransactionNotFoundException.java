package com.example.demo.exception;

public class PhysicalGoldTransactionNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 6969141845317221292L;

	public PhysicalGoldTransactionNotFoundException(String message) {
		super(message);
	}
}
