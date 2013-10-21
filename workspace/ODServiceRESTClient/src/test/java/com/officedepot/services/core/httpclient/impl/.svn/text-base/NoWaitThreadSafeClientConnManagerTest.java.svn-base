package com.officedepot.services.core.httpclient.impl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.services.core.httpclient.HttpClientParameters;

public class NoWaitThreadSafeClientConnManagerTest {

	private final static String DUMMY_HOSTNAME_1 = "http://wwwdevtrunk8.uschecomrnd.net/jenkins/";
	private final static String DUMMY_HOSTNAME_2 = "http://wwwdevtrunk8.uschecomrnd.net/jenkins/view/SOA/";	
	private final static int MAX_TOTAL = 5;
	
	private NoWaitThreadSafeClientConnManager ccm;
	private HttpClient httpClient;
	
	@Before
	public void setUp() {
		ccm = new NoWaitThreadSafeClientConnManager();
		NoWaitThreadSafeClientConnManager.setMaxConnections(ccm, buildHttpClientParameters());
		httpClient = new DefaultHttpClient(ccm);
	}
	
	@Test
	public void testReachMaxTotal() {
		System.out.println("testReachMaxTotal");
		execute(new HttpGet(DUMMY_HOSTNAME_1));
		execute(new HttpGet(DUMMY_HOSTNAME_2));
		execute(new HttpGet(DUMMY_HOSTNAME_1));
		execute(new HttpGet(DUMMY_HOSTNAME_2));	
		execute(new HttpGet(DUMMY_HOSTNAME_1));			
		try {
			execute(new HttpGet(DUMMY_HOSTNAME_2));				
			fail("Should have raised an exception");
		}
		catch (RuntimeException ex) {
			// OK
		}
	}
	
	private void execute(HttpUriRequest httpUriRequest) {
		try {
			httpClient.execute(httpUriRequest);
		}
		catch (ClientProtocolException e) {
			fail(e.getMessage());
		}
		catch (IOException e) {
			fail(e.getMessage());
		}	
	}
	
	private HttpClientParameters buildHttpClientParameters() {
		HttpClientParametersImpl result = new HttpClientParametersImpl();
		result.setMaxConnections(MAX_TOTAL);
		result.setConnectionTimeoutMillis(-1);
		result.setSocketTimeoutMillis(-1);
		return result;
	}	
}
