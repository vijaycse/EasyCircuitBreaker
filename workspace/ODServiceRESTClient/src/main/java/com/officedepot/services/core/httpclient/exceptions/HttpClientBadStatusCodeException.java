package com.officedepot.services.core.httpclient.exceptions;

public class HttpClientBadStatusCodeException extends HttpClientException {

	private static final long serialVersionUID = 2388640098268035901L;
	
	private String output;
	private int statusCode;

	public HttpClientBadStatusCodeException(String message, int statusCode, String output) {
		super(message);
		this.statusCode = statusCode;
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public int getStatusCode() {
		return statusCode;
	}	
}
