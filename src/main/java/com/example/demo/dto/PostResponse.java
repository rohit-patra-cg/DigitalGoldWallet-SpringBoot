package com.example.demo.dto;

import java.util.Date;

public class PostResponse {

	private final Date timeStamp;
	private final String message;

	public PostResponse(Date timeStamp, String message) {
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}
}
