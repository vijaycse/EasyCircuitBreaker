package com.officedepot.services.core.httpclient;

import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;

public interface HttpClientWrapper {
	
	// Without cookies
	
	HttpClientWrapperResponse get(String uri);	
	
	HttpClientWrapperResponse getJson(String uri);	

	HttpClientWrapperResponse postJson(String uri, String payload);
	
	HttpClientWrapperResponse postNvp(String uri, List<NameValuePair> nameValuePairs);
	
	HttpClientWrapperResponse post(String uri, List<NameValuePair> headers);
	
	// With cookies
	
	HttpClientWrapperResponse get(String uri, Set<Cookie> cookies);	

	HttpClientWrapperResponse postJson(String uri, String payload, Set<Cookie> cookies);
	
	HttpClientWrapperResponse postNvp(String uri, List<NameValuePair> nameValuePairs, Set<Cookie> cookies);	
}