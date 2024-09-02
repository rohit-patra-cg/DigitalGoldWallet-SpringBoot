package com.example.demo.exception;

public class UserNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 6352833693579277719L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
