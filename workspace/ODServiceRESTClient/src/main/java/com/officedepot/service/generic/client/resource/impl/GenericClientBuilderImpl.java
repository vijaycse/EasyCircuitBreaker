package com.officedepot.service.generic.client.resource.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neverquitter.service.generic.client.monitor.ServiceHealth;
import com.officedepot.service.generic.client.impl.GenericWebResourceConfigurer;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.resource.GenericClientBuilder;
import com.sun.jersey.api.client.WebResource.Builder;

public class GenericClientBuilderImpl  implements GenericClientBuilder{

	private ServiceHealth HEALTH_CHECK;
	private final static Logger LOGGER = Logger.getLogger(GenericClientBuilderImpl.class);

	public GenericClientBuilderImpl(ServiceHealth healthCheck){
		this.HEALTH_CHECK= healthCheck;
	}

	public ServiceHealth getHEALTH_CHECK() {
		return HEALTH_CHECK;
	}


	public void setHEALTH_CHECK(ServiceHealth hEALTH_CHECK) {
		HEALTH_CHECK = hEALTH_CHECK;
	}




	/***
	 *  Creating webresource with the minimal validation for client properties
	 * @param completeUriPath
	 * @param params
	 * @param clientProperties
	 * @return
	 * @throws GenericClientException
	 */
	public final Builder createWebResource(final String completeUriPath, final Map<String, String> params, final Map<String, String> clientProperties ,final Map<String,List<Object>> headersNamedValuePair,final Map<String,String> cookies) throws GenericClientException{

		String httpMethod = null;
		String contentType = null;
		String serviceName = null;
		short failureCountAllowed = 0;
		int connectionTimeout = 0;
		short maxConnection = 0;
		long breakTimer = 0;
		String basicAuthUsername = null;
		String basicAuthPassword = null;


		if(null!=clientProperties && clientProperties.size() > 0)
		{// obtaining the configuration properties from the map
			httpMethod  = clientProperties.get("httpMethod");
			contentType = clientProperties.get("contentType");
			serviceName = clientProperties.get("serviceName");

			failureCountAllowed = (short) Integer.parseInt(clientProperties.get("failureCountAllowed"));
			breakTimer  =  Long.parseLong(clientProperties.get("serviceBreakerTimer"));
			// it should be long but httpclient accepts only int. hence marking it as int
			connectionTimeout = Integer.parseInt(clientProperties.get("connectionTimeout"));
			maxConnection   = (short) Integer.parseInt(clientProperties.get("maxConnection"));
			basicAuthUsername = clientProperties.get("basicAuthenticationUsername");
			basicAuthPassword = clientProperties.get("basicAuthenticationPassword");

		}
		else{
			throw new GenericClientException("1004 ", "Client Properties are not set");
		}
		// obtaining the resource after health check
		LOGGER.debug("Service " + serviceName + " appears to be healthy");
		if(getHEALTH_CHECK().isHealthy(serviceName,failureCountAllowed,breakTimer)){
			return GenericWebResourceConfigurer.CONFIGURE_INSTANCE.getWebResourceForService(
					serviceName,
					completeUriPath, 
					params,
					maxConnection,
					connectionTimeout,
					httpMethod,
					contentType,
					basicAuthUsername,
					basicAuthPassword,
					headersNamedValuePair,cookies);
		}
		else {
			LOGGER.debug("Service " + completeUriPath + " is Unavailable now ");
			throw new GenericClientException(" Service " + completeUriPath 	+ " is unavailable  ", "Please try after some time..");
		}

	}

}
