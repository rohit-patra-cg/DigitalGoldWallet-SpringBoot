package com.example.demo.exception;

public class InvalidGoldPriceException extends Exception {

	private static final long serialVersionUID = -4355388328106768043L;

	public InvalidGoldPriceException(String message) {
		super(message);
	}
}
