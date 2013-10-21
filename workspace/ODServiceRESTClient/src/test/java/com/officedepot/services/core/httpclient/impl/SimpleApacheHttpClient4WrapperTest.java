package com.officedepot.services.core.httpclient.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.officedepot.services.core.httpclient.HttpClientParameters;

public class SimpleApacheHttpClient4WrapperTest {
	
	private final static HttpClientParameters parameters = createParameters();
	private SimpleApacheHttpClient4Wrapper wrapper = null;
	
	@Before
	public void setUp() {
		wrapper = new SimpleApacheHttpClient4Wrapper(parameters);
	}

	@Test
	public void testHttpsGet() {
		//
		// Not a regular unit test
		//
//		String html = wrapper.get("https://localhost/cart/checkout.do");
//		System.out.println(html);
//		assertTrue(html.length() > 0);
	}

	private static HttpClientParameters createParameters() {
		HttpClientParametersImpl result = new HttpClientParametersImpl();
		result.setConnectionTimeoutMillis(10000);
		result.setMaxConnections(10);
		result.setSocketTimeoutMillis(10000);
		return result;
	}	
}
