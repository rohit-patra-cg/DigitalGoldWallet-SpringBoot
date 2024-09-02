package com.example.demo.exception;

public class VendorNotFoundException extends ResourceNotFoundException {
	
	private static final long serialVersionUID = -4306434692718450102L;

	public VendorNotFoundException(String message) {
		super(message);
	}
}
