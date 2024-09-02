package com.example.demo.exception;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {

	private static final long serialVersionUID = -1491812956724203709L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
