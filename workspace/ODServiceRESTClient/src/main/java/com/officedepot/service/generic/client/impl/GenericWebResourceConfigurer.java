package com.officedepot.service.generic.client.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.ODGenericClient;
import com.officedepot.service.generic.client.ODGenericClientConfig;
import com.officedepot.service.generic.client.ODGenericHttpClientParametersImpl;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.services.core.httpclient.impl.HttpClientParametersImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public enum GenericWebResourceConfigurer {

	CONFIGURE_INSTANCE;
	
	GenericWebResourceConfigurer(){
		
	}

	private static final Logger LOGGER = Logger.getLogger(GenericWebResourceConfigurer.class);
	private static final HttpClientParametersImpl HTTP_CLIENT_PARAMS = new ODGenericHttpClientParametersImpl();
	private static final ReentrantLock lock =  new ReentrantLock(); 
	private static final Map<String, Client> CLIENT_POOL_REGISTER = new ConcurrentHashMap<String, Client>();
	/**
	 * 
	 * @param completeUriPath
	 * @param params
	 * @param socketTimeout 
	 * @param connectionTimeout 
	 * @param maxConnection 
	 * @param contentType 
	 * @param httpMethod 
	 * @return
	 * @throws GenericClientException
	 */
	public com.sun.jersey.api.client.WebResource.Builder getWebResourceForService(
			final String serviceName,	final String completeUriPath, final Map<String, String> params,final short maxConnection,final int connectionTimeout,final String httpMethod, 
			final String contentType,final String basicAuthUsername,final String basicAuthPassword, final Map<String,List<Object>> headersNamedValuePair,final Map<String,String> cookies) throws GenericClientException {
		if(!CLIENT_POOL_REGISTER.containsKey(serviceName)){
			createClientPool(serviceName,maxConnection);
		}
		return getWebResource(CLIENT_POOL_REGISTER.get(serviceName), completeUriPath, params,connectionTimeout,httpMethod,contentType,basicAuthUsername,basicAuthPassword,headersNamedValuePair,cookies);
	}


	/**
	 * 
	 * @param serviceName
	 * @param maxConnection
	 * @return
	 */

	private void createClientPool(String serviceName, short maxConnection) {
		if(null == CLIENT_POOL_REGISTER.get(serviceName)){
			lock.lock();	
			try{	
				if(null == CLIENT_POOL_REGISTER.get(serviceName)){
					LOGGER.debug("Creating Client Pool for " + serviceName);
					Client jerseyClient= getGenericClient(getClientConfig(maxConnection));
					CLIENT_POOL_REGISTER.put(serviceName, jerseyClient);
				}
			}
			finally {
				lock.unlock();
			}
		}
	}	


	/**
	 * 
	 * @param maxConnection 
	 * @return
	 */
	private void setHTTPParams(short maxConnection) {
		HTTP_CLIENT_PARAMS.setMaxConnections(maxConnection);
	}

	/**
	 * 
	 * @param maxConnection
	 * @param parameters
	 * @return
	 */
	private DefaultClientConfig getClientConfig(short maxConnection) {
		setHTTPParams(maxConnection);
		ODGenericClientConfig genericClientConfig = GenericRestClientConfig.CONFIG_INSTANCE;
		final DefaultClientConfig clientConfig = genericClientConfig
				.createGenericClientConfig(HTTP_CLIENT_PARAMS);
		return clientConfig;
	}

	/**
	 * 
	 * @param clientConfig
	 * @return
	 */

	private Client getGenericClient(DefaultClientConfig clientConfig) {
		ODGenericClient genericClient = GenericRestClient.CLIENT_INSTANCE;
		final Client client = genericClient.setRestClient(clientConfig);
		//client.addFilter(new LoggingFilter());
		return client;
	}

	/**
	 *  
	 * webresource builder configuration and building the request
	 * 
	 * @param genericClient
	 * @param completeUriPath
	 * @param params
	 * @param socketTimeout 
	 * @param connectionTimeout 
	 * @param contentType 
	 * @param httpMethod 
	 * @return
	 * @throws GenericClientException
	 */
	private Builder getWebResource(Client genericClient,
			String completeUriPath, Map<String, String> params, int connectionTimeout, String httpMethod, String contentType,
			String basicAuthUsername, String basicAuthPassword,Map<String,List<Object>>  headersNamedValuePair,Map<String,String> cookies) throws GenericClientException {
		params = (null == params) ? new HashMap<String, String>() : params;
		cookies =(null == cookies) ? new HashMap<String, String>() : cookies;
		headersNamedValuePair =(null == headersNamedValuePair) ? new HashMap<String, List<Object>>() : headersNamedValuePair;
		final GenericRestWebResource resource = GenericRestWebResource.WEBRESOURCE_INSTANCE;
		LOGGER.debug(" resource is obtained  for " + completeUriPath);
		return resource.buildRequest(genericClient, completeUriPath, params,connectionTimeout,httpMethod,contentType,basicAuthUsername,basicAuthPassword,headersNamedValuePair,cookies);
	}

}
