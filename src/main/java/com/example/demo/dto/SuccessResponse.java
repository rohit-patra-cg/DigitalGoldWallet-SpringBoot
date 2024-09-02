package com.example.demo.dto;

import java.util.Date;

public class SuccessResponse {

	private final Date timeStamp;
	private final String message;
	private final Integer entityId;

	public SuccessResponse(Date timeStamp, String message, Integer entityId) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.entityId = entityId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public Integer getEntityId() {
		return entityId;
	}

}
