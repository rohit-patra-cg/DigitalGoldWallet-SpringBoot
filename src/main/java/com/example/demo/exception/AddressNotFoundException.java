package com.example.demo.exception;

public class AddressNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = -3218796386937042233L;

	public AddressNotFoundException(String message) {
		super(message);
	}
}
