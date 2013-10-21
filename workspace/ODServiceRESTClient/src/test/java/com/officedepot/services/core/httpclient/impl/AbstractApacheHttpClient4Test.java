package com.officedepot.services.core.httpclient.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;
import com.officedepot.services.core.httpclient.exceptions.HttpClientException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientTimeoutException;

import static org.easymock.EasyMock.*;

public class AbstractApacheHttpClient4Test {
	
	private final static String DEFAULT_URI = "uri";
	private final static String DEFAULT_JSON = "{}";
	private final static String DEFAULT_EXCEPTION_MSG = "An exception has occurred";
	private final static String DEFAULT_RESPONSE = "A response";
	private final static int HTTP_500 = 500;
	
	private class MockApacheHttpClient4 extends AbstractApacheHttpClient4 {
		
		private HttpClient httpClient = createMock(HttpClient.class);

		@Override
		protected HttpClient getHttpClient() {
			return httpClient;
		}		
	}
	
	@Test
	public void testJson() {
		System.out.println("testJson");
		final HttpResponse httpResponse = createMock(HttpResponse.class);
		final HttpEntity httpEntity = createMock(HttpEntity.class);
		final StatusLine statusLine = createMock(StatusLine.class);
		final InputStream is = new ByteArrayInputStream(DEFAULT_RESPONSE.getBytes());
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(httpResponse.getStatusLine()).andReturn(statusLine).anyTimes();
			expect(statusLine.getStatusCode()).andReturn(HttpStatus.SC_OK);
			expect(httpResponse.getEntity()).andReturn(httpEntity).anyTimes();
			expect(httpEntity.getContent()).andReturn(is).anyTimes();			
			expect(client.execute(anyObject(HttpPost.class), anyObject(HttpContext.class))).
				andReturn(httpResponse);			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(httpResponse);
		replay(httpEntity);
		replay(statusLine);		
		replay(client);
		HttpClientWrapperResponse response = instance.postJson(DEFAULT_URI, DEFAULT_JSON);
		assertEquals(DEFAULT_RESPONSE, response.getData());
		assertEquals(0, response.getCookies().size());
		verify(httpResponse);
		verify(httpEntity);
		verify(statusLine);
		verify(client);
	}
	
	@Test
	public void testGetJson() {
		System.out.println("testGetJson");
		final HttpResponse httpResponse = createMock(HttpResponse.class);
		final HttpEntity httpEntity = createMock(HttpEntity.class);
		final StatusLine statusLine = createMock(StatusLine.class);
		final InputStream is = new ByteArrayInputStream(DEFAULT_RESPONSE.getBytes());
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(httpResponse.getStatusLine()).andReturn(statusLine).anyTimes();
			expect(statusLine.getStatusCode()).andReturn(HttpStatus.SC_OK);
			expect(httpResponse.getEntity()).andReturn(httpEntity).anyTimes();
			expect(httpEntity.getContent()).andReturn(is).anyTimes();			
			expect(client.execute(anyObject(HttpGet.class), anyObject(HttpContext.class))).
				andReturn(httpResponse);			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(httpResponse);
		replay(httpEntity);
		replay(statusLine);		
		replay(client);
		HttpClientWrapperResponse response = instance.getJson(DEFAULT_URI);
		assertEquals(DEFAULT_RESPONSE, response.getData());
		assertEquals(0, response.getCookies().size());
		verify(httpResponse);
		verify(httpEntity);
		verify(statusLine);
		verify(client);
	}
	
	
	@Test
	public void testNvp() {
		System.out.println("testNvp");
		final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("p1", "v1"));
		nameValuePairs.add(new BasicNameValuePair("p2", "v2"));		
		final HttpResponse httpResponse = createMock(HttpResponse.class);
		final HttpEntity httpEntity = createMock(HttpEntity.class);
		final StatusLine statusLine = createMock(StatusLine.class);
		final InputStream is = new ByteArrayInputStream(DEFAULT_RESPONSE.getBytes());
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(httpResponse.getStatusLine()).andReturn(statusLine).anyTimes();
			expect(statusLine.getStatusCode()).andReturn(HttpStatus.SC_OK);
			expect(httpResponse.getEntity()).andReturn(httpEntity).anyTimes();
			expect(httpEntity.getContent()).andReturn(is).anyTimes();			
			expect(client.execute(anyObject(HttpPost.class), anyObject(HttpContext.class))).
				andReturn(httpResponse);			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(httpResponse);
		replay(httpEntity);
		replay(statusLine);		
		replay(client);		
		HttpClientWrapperResponse response = instance.postNvp(DEFAULT_URI, nameValuePairs);
		assertEquals(DEFAULT_RESPONSE, response.getData());
		assertEquals(0, response.getCookies().size());
		verify(httpResponse);
		verify(httpEntity);
		verify(statusLine);
		verify(client);
	}	

	@Test
	public void testHandleHttpClientTimeoutException() {
		System.out.println("testHandleHttpClientTimeoutException");
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(client.execute(anyObject(HttpPost.class), anyObject(HttpContext.class))).
				andThrow(new ConnectTimeoutException(DEFAULT_EXCEPTION_MSG));			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(client);
		try {
			instance.postJson(DEFAULT_URI, DEFAULT_JSON);
			fail("Should have thrown HttpClientTimeoutException");
		}
		catch (HttpClientTimeoutException ex) {
			// OK
		}
		verify(client);
	}
	
	@Test
	public void testNullEntity() {
		System.out.println("testNullEntity");
		final HttpResponse httpResponse = createMock(HttpResponse.class);
		final StatusLine statusLine = createMock(StatusLine.class);
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(httpResponse.getStatusLine()).andReturn(statusLine);
			expect(statusLine.getStatusCode()).andReturn(HTTP_500);
			expect(httpResponse.getEntity()).andReturn(null);			
			expect(client.execute(anyObject(HttpPost.class), anyObject(HttpContext.class))).
				andReturn(httpResponse);			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(httpResponse);
		replay(client);
		try {
			instance.postJson(DEFAULT_URI, DEFAULT_JSON);
			fail("Should have thrown HttpClientException");
		}
		catch (HttpClientException ex) {
			assertEquals("Entity is null", ex.getMessage());
		}
		verify(httpResponse);	
		verify(client);
	}	
	
	@Test
	public void testNullContent() {
		System.out.println("testNullContent");
		final HttpResponse httpResponse = createMock(HttpResponse.class);
		final HttpEntity httpEntity = createMock(HttpEntity.class);
		final StatusLine statusLine = createMock(StatusLine.class);
		MockApacheHttpClient4 instance = new MockApacheHttpClient4();
		final HttpClient client = instance.getHttpClient();
		try {
			expect(httpResponse.getStatusLine()).andReturn(statusLine);
			expect(statusLine.getStatusCode()).andReturn(HTTP_500);
			expect(httpResponse.getEntity()).andReturn(httpEntity).anyTimes();
			expect(httpEntity.getContent()).andReturn(null);			
			expect(client.execute(anyObject(HttpPost.class), anyObject(HttpContext.class))).
				andReturn(httpResponse);			
		}
		catch (Exception ex) {
			fail(ex.getMessage());
		}
		replay(httpResponse);
		replay(httpEntity);
		replay(client);
		try {
			instance.postJson(DEFAULT_URI, DEFAULT_JSON);
			fail("Should have thrown HttpClientException");
		}
		catch (HttpClientException ex) {
			assertEquals("Entity content is null", ex.getMessage());
		}
		verify(httpResponse);
		verify(httpEntity);		
		verify(client);
	}
}
