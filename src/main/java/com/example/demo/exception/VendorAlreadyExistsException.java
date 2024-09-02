package com.example.demo.exception;

public class VendorAlreadyExistsException extends ResourceAlreadyExistsException {

	private static final long serialVersionUID = 4892862399242161958L;

	public VendorAlreadyExistsException(String message) {
		super(message);
	}
}

