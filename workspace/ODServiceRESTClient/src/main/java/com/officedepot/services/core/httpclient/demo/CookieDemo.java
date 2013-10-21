package com.officedepot.services.core.httpclient.demo;

import java.util.Set;

import org.apache.http.cookie.Cookie;

import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;
import com.officedepot.services.core.httpclient.impl.CertificateEnabledApacheHttpClient4Wrapper;
import com.officedepot.services.core.httpclient.impl.HttpClientParametersImpl;
import com.officedepot.services.core.httpclient.impl.SimpleApacheHttpClient4Wrapper;

public class CookieDemo {
	
	private final static String URL = "http://www.google.com";
	private final static int MAX_CONNECTIONS = 10;
	private final static int CONNECTION_TIMEOUT = 10000;
	private final static int SOCKET_TIMEOUT = 10000;
	
	public static void main(String[] args) {
		System.out.println("Simple");
		simpleApacheHttpClient4Wrapper();
		System.out.println("Certificate enabled");
		certificateEnabledApacheHttpClient4Wrapper();
	}
	
	private static void simpleApacheHttpClient4Wrapper() {
		rememberCookies(new SimpleApacheHttpClient4Wrapper(createParameters()));
		doNotRememberCookies(new SimpleApacheHttpClient4Wrapper(createParameters()));		
	}
	
	private static void certificateEnabledApacheHttpClient4Wrapper() {
		rememberCookies(createCertificateEnabledApacheHttpClient4Wrapper());
		doNotRememberCookies(createCertificateEnabledApacheHttpClient4Wrapper());		
	}	
	
	private static void rememberCookies(HttpClientWrapper client) {
		HttpClientWrapperResponse response1 = client.get(URL);
		HttpClientWrapperResponse response2 = client.get(URL, response1.getCookies());
		System.out.println("Remember cookies between requests");
		compareCookies(response1.getCookies(), response2.getCookies());
	}
	
	private static void doNotRememberCookies(HttpClientWrapper client) {
		System.out.println("Forget cookies between requests");		
		compareCookies(client.get(URL).getCookies(), client.get(URL).getCookies());
	}	
	
	private static void compareCookies(Set<Cookie> firstSet, Set<Cookie> secondSet) {
		int nbEquals = 0;
		int nbNotEquals = 0;
		for (Cookie cookie : firstSet) {
			if (secondSet.contains(cookie)) {
				nbEquals++;
			}
			else {
				nbNotEquals++;
			}
		}
		System.out.println("# equals = " + nbEquals + ", # not equals = " + nbNotEquals);		
	}
	
	private static HttpClientWrapper createCertificateEnabledApacheHttpClient4Wrapper() {
		CertificateEnabledApacheHttpClient4Wrapper result = new CertificateEnabledApacheHttpClient4Wrapper();
		result.setHttpClientParameters(createParameters());
		result.postConstruct();
		return result;
	}
	
	private static HttpClientParameters createParameters() {
		HttpClientParametersImpl result = new HttpClientParametersImpl();
		result.setMaxConnections(MAX_CONNECTIONS);
		result.setConnectionTimeoutMillis(CONNECTION_TIMEOUT);
		result.setSocketTimeoutMillis(SOCKET_TIMEOUT);
		return result;
	}
}
