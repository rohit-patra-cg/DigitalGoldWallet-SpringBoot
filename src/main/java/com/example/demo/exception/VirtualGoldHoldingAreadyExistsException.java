package com.example.demo.exception;

public class VirtualGoldHoldingAreadyExistsException extends ResourceAlreadyExistsException {

	private static final long serialVersionUID = -8503419328873536631L;

	public VirtualGoldHoldingAreadyExistsException(String message) {
		super(message);
	}
}
