package com.example.demo.exception;

public class InvalidGoldQuantityException extends Exception {

	private static final long serialVersionUID = 4810960781148451070L;

	public InvalidGoldQuantityException(String message) {
		super(message);
	}
}
