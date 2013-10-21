package com.officedepot.services.core.http.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;
import com.officedepot.services.core.httpclient.impl.HttpClientWrapperResponseImpl;
import com.officedepot.services.core.model.ServiceMethodActualParameter;
import com.officedepot.services.core.model.ServiceMethodCall.HttpMethodType;
import com.officedepot.services.core.model.ServiceMethodCookie;
import com.officedepot.services.core.model.exceptions.TransportException;
import com.officedepot.services.core.model.impl.ServiceMethodActualParameterImpl;

import static org.easymock.EasyMock.*;

public class BaseHttpClientImplTest {
	
	private final static String DEFAULT_URI = "uri";
	private final static String DEFAULT_JSON = "{}";
	private final static Set<Cookie> NO_APACHE_COOKIE = new HashSet<Cookie>();	
	private final static Set<ServiceMethodCookie> NO_SERVICE_METHOD_COOKIE = new HashSet<ServiceMethodCookie>();
	private final static HttpClientWrapperResponse EMPTY_RESPONSE = new HttpClientWrapperResponseImpl("", new HashSet<Cookie>());
	
	private BaseHttpClientImpl instance;
	private HttpClientWrapper httpClientWrapper;
	
	@Before
	public void setUp() {
		httpClientWrapper = createMock(HttpClientWrapper.class);
		instance = new BaseHttpClientImpl();
		instance.setHttpClientWrapper(httpClientWrapper);
	}
	
	@Test
	public void testJson() {
		System.out.println("testJson");
		expect(httpClientWrapper.postJson(DEFAULT_URI, DEFAULT_JSON, NO_APACHE_COOKIE)).andReturn(EMPTY_RESPONSE);
		replay(httpClientWrapper);
		instance.executeJson(DEFAULT_URI, HttpMethodType.POST, DEFAULT_JSON, NO_SERVICE_METHOD_COOKIE);
		verify(httpClientWrapper);
	}
	
	
	@Test
	public void testGetJson() {
		System.out.println("testGetJson");
		expect(httpClientWrapper.getJson(DEFAULT_URI)).andReturn(EMPTY_RESPONSE);
		replay(httpClientWrapper);
		instance.executeGetJson(DEFAULT_URI);
		verify(httpClientWrapper);
	}
	
		
	@Test
	public void testNvp() {
		System.out.println("testNvp");
		List<ServiceMethodActualParameter> serviceMethodActualParameters = new ArrayList<ServiceMethodActualParameter>();
		serviceMethodActualParameters.add(new ServiceMethodActualParameterImpl("p1", "v1"));
		serviceMethodActualParameters.add(new ServiceMethodActualParameterImpl("p2", "v2"));
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("p1", "v1"));
		nameValuePairs.add(new BasicNameValuePair("p2", "v2"));		
		expect(httpClientWrapper.postNvp(DEFAULT_URI, nameValuePairs, NO_APACHE_COOKIE)).andReturn(EMPTY_RESPONSE);
		replay(httpClientWrapper);
		instance.executeNvp(DEFAULT_URI, HttpMethodType.POST, serviceMethodActualParameters, NO_SERVICE_METHOD_COOKIE);
		verify(httpClientWrapper);
	}	
	
	@Test
	public void testOnlyPostSupportedJson() {
		System.out.println("testOnlyPostSupportedJson");		
		try {
			instance.executeJson(DEFAULT_URI, HttpMethodType.GET, DEFAULT_JSON, NO_SERVICE_METHOD_COOKIE);
			fail("Only POST is currently supported");
		}
		catch (TransportException ex) {
			// OK
		}		
	}
	
	@Test
	public void testOnlyPostSupportedNvp() {
		System.out.println("testOnlyPostSupportedNvp");		
		try {
			instance.executeNvp(DEFAULT_URI, HttpMethodType.GET, null, NO_SERVICE_METHOD_COOKIE);
			fail("Only POST is currently supported");
		}
		catch (TransportException ex) {
			// OK
		}		
	}		
}
