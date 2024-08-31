package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class ValidationErrorDetails {
	
	private final Date timeStamp;
	private final List<String> errorMessages;

	public ValidationErrorDetails(Date timeStamp, List<String> errorMessages) {
		this.timeStamp = timeStamp;
		this.errorMessages = errorMessages;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
}
