package com.saulo.borges.roihunter.util;

public class JsonResponse {

	private String message;

	private String status;

	public JsonResponse() {
	}

	public JsonResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
