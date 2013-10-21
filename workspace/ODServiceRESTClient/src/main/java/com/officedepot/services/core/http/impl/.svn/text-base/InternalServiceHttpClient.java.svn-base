package com.officedepot.services.core.http.impl;

import java.util.List;
import java.util.Set;

import com.officedepot.services.core.httpclient.exceptions.HttpClientBadStatusCodeException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientNoConnectionAvailableException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientTimeoutException;
import com.officedepot.services.core.model.JsonConverter;
import com.officedepot.services.core.model.ServiceMethodActualParameter;
import com.officedepot.services.core.model.ServiceMethodActualResult;
import com.officedepot.services.core.model.ServiceMethodCookie;
import com.officedepot.services.core.model.ServiceMethodCall.HttpMethodType;
import com.officedepot.services.core.model.exceptions.NoConnectionAvailableException;
import com.officedepot.services.core.model.exceptions.ServiceRuntimeException;
import com.officedepot.services.core.model.exceptions.TimeoutException;
import com.officedepot.services.core.model.exceptions.TransportException;
import com.officedepot.services.core.model.impl.JsonConverterImpl;

public class InternalServiceHttpClient extends BaseHttpClientImpl {
	
	private static JsonConverter<ServiceRuntimeException> serviceRuntimeExceptionConverter =
			new JsonConverterImpl<ServiceRuntimeException>();		
	
	@Override
	public ServiceMethodActualResult executeJson(String uri, HttpMethodType methodType, String json, Set<ServiceMethodCookie> cookies) {
		try {
			return super.executeJson(uri, methodType, json, cookies);
		}
		catch (Exception ex) {
			throw translateException(ex);
		}
	}

	@Override
	public ServiceMethodActualResult executeNvp(String uri, HttpMethodType methodType, List<ServiceMethodActualParameter> parameters, Set<ServiceMethodCookie> cookies) {
		try {
			return super.executeNvp(uri, methodType, parameters, cookies);
		}
		catch (Exception ex) {
			throw translateException(ex);
		}
	}
	
	public ServiceMethodActualResult executeGetJson(String uri){
	try {
		return super.executeGetJson(uri);
	}
	catch (Exception ex) {
		throw translateException(ex);
	}
	}
	
	private ServiceRuntimeException translateException(Exception ex) {
		if (ex instanceof HttpClientBadStatusCodeException) {
			return serviceRuntimeExceptionConverter.fromJson(
				((HttpClientBadStatusCodeException) ex).getOutput(), ServiceRuntimeException.class);
		}
		if (ex instanceof HttpClientNoConnectionAvailableException) {
			return new NoConnectionAvailableException(ex.getMessage());
		}
		if (ex instanceof HttpClientTimeoutException) {
			return new TimeoutException(ex.getMessage());
		}		
		if (ex instanceof HttpClientException) {
			return new TransportException(ex.getMessage());
		}
		return new TransportException(ex.getMessage());
	}
}
