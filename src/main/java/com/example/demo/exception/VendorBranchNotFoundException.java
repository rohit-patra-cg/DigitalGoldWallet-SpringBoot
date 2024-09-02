package com.example.demo.exception;

public class VendorBranchNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = -996322167957239192L;

	public VendorBranchNotFoundException(String message) {
		super(message);
	}
}
