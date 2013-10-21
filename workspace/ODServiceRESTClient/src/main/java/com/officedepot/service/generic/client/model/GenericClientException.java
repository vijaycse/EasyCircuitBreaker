package com.officedepot.service.generic.client.model;

public class GenericClientException extends Exception {

	/**
	 * this exception is called for Rest based services based exception.
	 */
	private static final long serialVersionUID = 1L;

	public GenericClientException(String errorID, String errorMsg) {
		this.errorCode = errorID;
		this.errorString = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	private String errorCode;

	private String errorString;

	public String toString() {

		return errorCode + ":" + errorString;
	}

}
