package com.example.demo.exception;

public class ResourceAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 3120082458296350144L;

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
