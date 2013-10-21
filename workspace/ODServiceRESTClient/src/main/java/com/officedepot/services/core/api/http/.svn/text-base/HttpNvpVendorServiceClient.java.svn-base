package com.officedepot.services.core.api.http;

import org.apache.log4j.Logger;

import com.officedepot.services.core.api.VendorServiceClient;
import com.officedepot.services.core.http.ServiceHttpClient;
import com.officedepot.services.core.model.ServiceMethodActualResult;
import com.officedepot.services.core.model.ServiceMethodCall;
import com.officedepot.services.core.model.exceptions.ServiceRuntimeException;

public class HttpNvpVendorServiceClient implements VendorServiceClient {
	
	private final static Logger log = Logger.getLogger(HttpNvpVendorServiceClient.class);

	private ServiceHttpClient vendorServiceHttpClient;

	public ServiceMethodActualResult execute(ServiceMethodCall call) {
		log.debug("Executing: " + call);
		ServiceMethodActualResult result = null;
		if (call.getHeaders().size() > 0 && 0 == call.getParameters().size()) {
			result = vendorServiceHttpClient.execute(
					call.getUri(),
					call.getHttpMethodType(),
					call.getHeaders()
			);			
		}
		else if (0 == call.getHeaders().size()) {
			result = vendorServiceHttpClient.executeNvp(
					call.getUri(),
					call.getHttpMethodType(),
					call.getParameters(),
					call.getCookies()
			);
		}
		else {
			// Needs refactoring
			throw new ServiceRuntimeException("Unsupported parameters");
		}
		log.debug("Result: " + result);
		return result;
	}

	public void setVendorServiceHttpClient(ServiceHttpClient vendorServiceHttpClient) {
		this.vendorServiceHttpClient = vendorServiceHttpClient;
	}	
}