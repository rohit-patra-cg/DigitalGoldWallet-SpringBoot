package com.example.demo.exception;

public class InvalidAmountException extends Exception {

	private static final long serialVersionUID = -282474755669252337L;

	public InvalidAmountException(String message) {
		super(message);
	}
}
