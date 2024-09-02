package com.example.demo.exception;

public class VirtualGoldHoldingNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 6408922112524721514L;

	public VirtualGoldHoldingNotFoundException(String message) {
		super(message);
	}
}
