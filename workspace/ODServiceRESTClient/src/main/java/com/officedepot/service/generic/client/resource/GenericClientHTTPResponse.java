package com.officedepot.service.generic.client.resource;

import com.officedepot.service.generic.client.impl.ODServiceRESTClientResponse;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.sun.jersey.api.client.WebResource.Builder;

public interface GenericClientHTTPResponse {

	 <T> T  obtainGenericTypeResponse(Builder requestResource,
				 Class<T> expectedPojo,  String serviceName, String httpMethod, short minThreds,  short maxThreads, long keepAliveTimeout, short queueSize) throws GenericClientException;
	 
	 <T> ODServiceRESTClientResponse obtainHttpResponse( Builder requestResource, String serviceName, String httpMethod,  short minThreads,  short maxThreads,
				 long keepAliveTimeout, 	short queueSize) throws GenericClientException;
	 
	}
