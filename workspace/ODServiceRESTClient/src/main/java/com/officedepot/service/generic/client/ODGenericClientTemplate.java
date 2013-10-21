package com.officedepot.service.generic.client;

import java.util.List;
import java.util.Map;

import com.officedepot.service.generic.client.impl.ODServiceRESTClientResponse;
import com.officedepot.service.generic.client.model.GenericClientException;

public interface ODGenericClientTemplate {
	
	 <T> T invokeRestTemplate(String completeUriPath,
			Map<String, String> params,
			Class<T> expectedPojo,Map<String,String> properties) throws GenericClientException;
	
	
	 <T> T invokeRestTemplateWithRequestHeaders(String completeUriPath,
				Map<String, String> params,
				Class<T> expectedPojo,Map<String,String> properties,Map<String,List<Object>> headersNamedValuePair,Map<String,String> cookies) throws GenericClientException;
		
	
	 ODServiceRESTClientResponse invokeRestTemplateReturnsEntityResponse(String completeUriPath,
				Map<String, String> params,
				Map<String,String> properties) throws GenericClientException;
	 
	 
	 ODServiceRESTClientResponse invokeRestTemplateReturnsEntityResponseWithRequestHeaders(String completeUriPath,
				Map<String, String> params,
				Map<String,String> properties,Map<String,List<Object>> headersNamedValuePair,Map<String,String> cookies) throws GenericClientException;
	
}
