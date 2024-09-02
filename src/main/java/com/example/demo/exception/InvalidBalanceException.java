package com.example.demo.exception;

public class InvalidBalanceException extends Exception {

	private static final long serialVersionUID = 3388743285359458268L;

	public InvalidBalanceException(String message) {
		super(message);
	}
}
