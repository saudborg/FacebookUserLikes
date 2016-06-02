package com.saulo.borges.roihunter.exception;

public class AppException extends Exception {

	private static final long serialVersionUID = -4454531872952074931L;

	/**
	 * contains redundantly the HTTP status of the response sent back to the
	 * client in case of error, so that the developer does not have to look into
	 * the response headers. If null a default
	 */
	Integer status;

	/** application specific error code */
	int code;

	/**
	 * 
	 * @param status
	 * @param code
	 * @param message
	 * @param link
	 */
	public AppException(int status, int code, String message) {
		super(message);
		this.status = status;
		this.code = code;
	}

	public AppException() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}