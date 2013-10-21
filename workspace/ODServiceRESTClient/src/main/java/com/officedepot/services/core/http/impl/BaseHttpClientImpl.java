package com.officedepot.services.core.http.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;


import com.officedepot.services.core.http.ServiceHttpClient;
import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;
import com.officedepot.services.core.model.ServiceMethodActualParameter;
import com.officedepot.services.core.model.ServiceMethodActualResult;
import com.officedepot.services.core.model.ServiceMethodCall.HttpMethodType;
import com.officedepot.services.core.model.ServiceMethodCookie;
import com.officedepot.services.core.model.exceptions.TransportException;
import com.officedepot.services.core.model.impl.ServiceMethodActualResultImpl;
import com.officedepot.services.core.model.impl.ServiceMethodCookieImpl;

public class BaseHttpClientImpl implements ServiceHttpClient {
	
	private final static Logger log = Logger.getLogger(BaseHttpClientImpl.class);	
	
	private HttpClientWrapper httpClientWrapper;

	public ServiceMethodActualResult execute(String uri, HttpMethodType methodType, List<ServiceMethodActualParameter> headers) {
		enforceHttpMethodType(HttpMethodType.POST, methodType);
		return createServiceMethodActualResult(httpClientWrapper.post(uri, buildNameValuePairs(headers)));
	}	

	public ServiceMethodActualResult executeJson(String uri, HttpMethodType methodType, String json, Set<ServiceMethodCookie> cookies) {
		enforceHttpMethodType(HttpMethodType.POST, methodType);
		return createServiceMethodActualResult(httpClientWrapper.postJson(uri, json, convertCookies(cookies)));
	}
		
	public ServiceMethodActualResult executeGetJson(String uri) {
		return createServiceMethodActualResult(httpClientWrapper.getJson(uri));
	}
	
	public ServiceMethodActualResult executeNvp(String uri, HttpMethodType methodType, List<ServiceMethodActualParameter> parameters, Set<ServiceMethodCookie> cookies) {
		enforceHttpMethodType(HttpMethodType.POST, methodType);
		return createServiceMethodActualResult(httpClientWrapper.postNvp(uri, buildNameValuePairs(parameters), convertCookies(cookies)));	
	}

	private void enforceHttpMethodType(HttpMethodType allowed, HttpMethodType actual) {
		if (allowed != actual) {
			final String errorMsg = "Only POST is currently supported.";
			log.error(errorMsg);
			throw new TransportException(errorMsg);
		}
	}
	
	private List<NameValuePair> buildNameValuePairs(List<ServiceMethodActualParameter> parameters) {
		List<NameValuePair> result = new ArrayList<NameValuePair>();
		for (ServiceMethodActualParameter parameter : parameters) {
			result.add(new BasicNameValuePair(parameter.getName(), parameter.getStringValue()));
		}
		return result;
	}
	
	public void setHttpClientWrapper(HttpClientWrapper httpClientWrapper) {
		this.httpClientWrapper = httpClientWrapper;
	}

	private static ServiceMethodActualResult createServiceMethodActualResult(HttpClientWrapperResponse httpClientWrapperResponse) {
		return new ServiceMethodActualResultImpl(
				httpClientWrapperResponse.getData(),
				extractCookies(httpClientWrapperResponse)
		);
	}		
	
	private static Set<ServiceMethodCookie> extractCookies(HttpClientWrapperResponse httpClientWrapperResponse) {
		Set<ServiceMethodCookie> result = new HashSet<ServiceMethodCookie>();
		for (Cookie cookie : httpClientWrapperResponse.getCookies()) {
			result.add(createServiceMethodCookie(cookie));
		}
		return result;
	}
	
	private static Set<Cookie> convertCookies(Set<ServiceMethodCookie> cookies) {
		Set<Cookie> result = new HashSet<Cookie>();
		for (ServiceMethodCookie cookie : cookies) {
			result.add(createClientCookie(cookie));
		}
		return result;
	}	
	
	private static ServiceMethodCookie createServiceMethodCookie(Cookie cookie) {
		ServiceMethodCookieImpl result = new ServiceMethodCookieImpl();
		result.setComment(cookie.getComment());
		result.setCommentURL(cookie.getCommentURL());
		result.setDomain(cookie.getDomain());
		result.setExpiryDate(cookie.getExpiryDate());
		result.setName(cookie.getName());
		result.setPath(cookie.getPath());
		result.setPersistent(cookie.isPersistent());
		result.setPorts(cookie.getPorts());
		result.setSecure(cookie.isSecure());
		result.setValue(cookie.getValue());
		result.setVersion(cookie.getVersion());
		return result;
	}
	
	private static Cookie createClientCookie(ServiceMethodCookie serviceMethodCookie) {
		BasicClientCookie2 result = new BasicClientCookie2(serviceMethodCookie.getName(), serviceMethodCookie.getValue());
		result.setComment(serviceMethodCookie.getComment());
		result.setCommentURL(serviceMethodCookie.getCommentURL());
		result.setDomain(serviceMethodCookie.getDomain());
		result.setExpiryDate(serviceMethodCookie.getExpiryDate());
		result.setPath(serviceMethodCookie.getPath());
		result.setPorts(serviceMethodCookie.getPorts());
		result.setSecure(serviceMethodCookie.isSecure());
		result.setVersion(serviceMethodCookie.getVersion());
		return result;
	}	
}
