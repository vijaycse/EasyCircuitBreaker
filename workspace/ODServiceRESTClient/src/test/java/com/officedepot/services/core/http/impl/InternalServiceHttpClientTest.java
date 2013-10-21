package com.officedepot.services.core.http.impl;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.exceptions.HttpClientBadStatusCodeException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientNoConnectionAvailableException;
import com.officedepot.services.core.model.ServiceMethodCookie;
import com.officedepot.services.core.model.ServiceMethodCall.HttpMethodType;
import com.officedepot.services.core.model.exceptions.NoConnectionAvailableException;
import com.officedepot.services.core.model.exceptions.TimeoutException;

import static org.easymock.EasyMock.*;

public class InternalServiceHttpClientTest {
	
	private final static String DEFAULT_URI = "uri";
	private final static String DEFAULT_JSON = "{}";
	private final static Set<ServiceMethodCookie> NO_SERVICE_METHOD_COOKIE = new HashSet<ServiceMethodCookie>();
	private final static Set<Cookie> NO_APACHE_COOKIE = new HashSet<Cookie>();	
	private final static String DEFAULT_ERROR_MSG = "error message";
	private final static int HTTP_500 = 500;	
	private final static String TIMEOUT_EXCEPTION_JSON =
		"{\"@class\":\"com.officedepot.services.core.model.exceptions.TimeoutException\",\"message\":" +
	    "\"" + DEFAULT_ERROR_MSG +  "\"}";
	
	private BaseHttpClientImpl instance;
	private HttpClientWrapper httpClientWrapper;
	
	@Before
	public void setUp() {
		httpClientWrapper = createMock(HttpClientWrapper.class);
		instance = new InternalServiceHttpClient();
		instance.setHttpClientWrapper(httpClientWrapper);
	}
	
	@Test
	public void testTranslateHttpClientNoConnectionAvailableException() {
		System.out.println("testTranslateHttpClientNoConnectionAvailableException");
		expect(httpClientWrapper.postJson(DEFAULT_URI, DEFAULT_JSON, NO_APACHE_COOKIE)).
			andThrow(new HttpClientNoConnectionAvailableException(DEFAULT_ERROR_MSG));
		replay(httpClientWrapper);
		try {
			instance.executeJson(DEFAULT_URI, HttpMethodType.POST, DEFAULT_JSON, NO_SERVICE_METHOD_COOKIE);
			fail("NoConnectionAvailableException should have been thrown");
		}
		catch (NoConnectionAvailableException ex) {
			assertEquals(DEFAULT_ERROR_MSG, ex.getMessage());
		}
		verify(httpClientWrapper);
	}
	
	@Test
	public void testTranslateHttpClientBadStatusCodeException() {
		System.out.println("testTranslateHttpClientBadStatusCodeException");		
		expect(httpClientWrapper.postJson(DEFAULT_URI, DEFAULT_JSON, NO_APACHE_COOKIE)).
				andThrow(new HttpClientBadStatusCodeException("", HTTP_500, TIMEOUT_EXCEPTION_JSON));
		replay(httpClientWrapper);
		try {
			instance.executeJson(DEFAULT_URI, HttpMethodType.POST, DEFAULT_JSON, NO_SERVICE_METHOD_COOKIE);
		}
		catch (TimeoutException ex) {
			assertEquals(DEFAULT_ERROR_MSG, ex.getMessage());
		}
		verify(httpClientWrapper);
	}	
}
