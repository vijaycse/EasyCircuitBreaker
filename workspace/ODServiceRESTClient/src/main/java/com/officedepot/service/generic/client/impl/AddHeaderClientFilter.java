package com.officedepot.service.generic.client.impl;

import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

public class AddHeaderClientFilter  extends ClientFilter{
	
	private Map<String,List<Object>> headersNamedValuePair;
	
	public AddHeaderClientFilter(Map<String,List<Object>> headersNamedValuePair){
		this.headersNamedValuePair=headersNamedValuePair;
	}
	
	
	@Override
	public ClientResponse handle(ClientRequest clientRequest)
			throws ClientHandlerException {
		final Map<String, List<Object>> headers = clientRequest.getHeaders();
		headers.putAll(headersNamedValuePair);
		return getNext().handle(clientRequest);
	}
	
	
}
