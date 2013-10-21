package com.officedepot.services.core.httpclient.exceptions;

public class HttpClientTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 4724025767904721333L;

	public HttpClientTimeoutException(String message) {
		super(message);
	}
}
