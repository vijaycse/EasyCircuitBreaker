package com.officedepot.service.generic.client.resource;

import java.util.List;
import java.util.Map;

import com.officedepot.service.generic.client.model.GenericClientException;
import com.sun.jersey.api.client.WebResource.Builder;

public interface GenericClientBuilder {

	Builder createWebResource( String completeUriPath,  Map<String, String> params,  Map<String, String> clientProperties,Map<String,List<Object>> headersNamedValuePair,Map<String,String> cookies) throws GenericClientException;
}

