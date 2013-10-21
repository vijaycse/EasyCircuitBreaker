package com.officedepot.services.core.httpclient.exceptions;

public class HttpClientNoConnectionAvailableException extends RuntimeException {

	private static final long serialVersionUID = -2685470927002550486L;

	public HttpClientNoConnectionAvailableException(String message) {
		super(message);
	}
}
