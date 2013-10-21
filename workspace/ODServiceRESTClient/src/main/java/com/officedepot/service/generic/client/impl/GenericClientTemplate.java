package com.officedepot.service.generic.client.impl;

import static com.officedepot.service.generic.client.util.GenericClientUtility.dateFormatter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;

import com.neverquitter.service.generic.client.monitor.ServiceHealth;
import com.neverquitter.service.generic.client.monitor.impl.ServiceHealthCheck;
import com.officedepot.service.generic.client.ODGenericClientTemplate;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.resource.impl.GenericClientBuilderImpl;
import com.officedepot.service.generic.client.resource.impl.GenericClientHTTPResponseImpl;

// immutable class

public final class GenericClientTemplate implements ODGenericClientTemplate {

	private final static Logger LOGGER = Logger.getLogger(GenericClientTemplate.class);
	private final static ServiceHealth HEALTH_CHECK = ServiceHealthCheck.HEALTH_CHECK_INSTANCE;

	public ServiceHealth getHealthCheck() {
		return HEALTH_CHECK;
	}

	/**
	 * use executor factory to instantiate
	 */
	protected GenericClientTemplate() {
	}

	/**
	 * This invokeRestTemplate provides a generic response object 
	 * <p>
	 * Notes: This is by default marshalls the JSON. XML is not supported yet.
	 * </p>
	 * 
	 * 
	 * @param completeUriPath
	 * @param params
	 * @param expectedPojo
	 * @return T
	 * @throws GenericClientException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public <T> T invokeRestTemplate(final String completeUriPath,final Map<String, String> params,final Class<T> expectedPojo,final Map<String,String> clientProperties) 
			throws GenericClientException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + "invokeTemplate - GenericTemplate  with  " +  completeUriPath +" " + dateFormatter().format(new Date(System.currentTimeMillis())));
		}

		return  new GenericClientHTTPResponseImpl(getHealthCheck()).obtainGenericTypeResponse(
				new GenericClientBuilderImpl(getHealthCheck()).createWebResource(completeUriPath, params, clientProperties,new HashMap<String, List<Object>>(),new HashMap<String,String>()), 
				expectedPojo,
				clientProperties.get("serviceName"),
				clientProperties.get("httpMethod"),
				(short) Integer.parseInt(clientProperties.get("minThreads")),
				(short) Integer.parseInt(clientProperties.get("maxThreads")),
				Long.parseLong(clientProperties.get("keepAliveTimeout")),
				(short) Integer.parseInt(clientProperties.get("queueSize")));
	}  



	/**
	 * This invokeRestTemplate provides a generic response object  with an option to add headers
	 * <p>
	 * Notes: This is by default marshalls the JSON. XML is not supported yet.
	 * </p>
	 * 
	 * 
	 * @param completeUriPath
	 * @param params
	 * @param expectedPojo
	 * @return T
	 * @throws GenericClientException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public <T> T invokeRestTemplateWithRequestHeaders(final String completeUriPath,final Map<String, String> params,final Class<T> expectedPojo,final Map<String,String> clientProperties,final Map<String,List<Object>> headersNamedValuePair,final Map<String,String> cookies) 
			throws GenericClientException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + "invokeTemplate - GenericTemplate  with  " +  completeUriPath +" " + dateFormatter().format(new Date(System.currentTimeMillis())));
		}

		return  new GenericClientHTTPResponseImpl(getHealthCheck()).obtainGenericTypeResponse(
				new GenericClientBuilderImpl(getHealthCheck()).createWebResource(completeUriPath, params, clientProperties,headersNamedValuePair,cookies), 
				expectedPojo,
				clientProperties.get("serviceName"),
				clientProperties.get("httpMethod"),
				(short) Integer.parseInt(clientProperties.get("minThreads")),
				(short) Integer.parseInt(clientProperties.get("maxThreads")),
				Long.parseLong(clientProperties.get("keepAliveTimeout")),
				(short) Integer.parseInt(clientProperties.get("queueSize")));
	}  

	
	/**
	 *  This method is used for consumers with the expectation to obtain the whole http response with additional option for setting headers and cookies
	 * <p>
	 * Notes: This is by default marshalls the JSON. XML is not supported yet.
	 * </p>
	 * 
	 * 
	 * @param completeUriPath
	 * @param params
	 * @param expectedPojo
	 * @return T
	 * @throws GenericClientException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public ODServiceRESTClientResponse invokeRestTemplateReturnsEntityResponseWithRequestHeaders(final String completeUriPath,final Map<String, String> params,final Map<String,String> clientProperties,final Map<String,List<Object>> headersNamedValuePair,final Map<String,String> cookies) 
			throws GenericClientException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + "invokeTemplate - GenericTemplate  with  " +  completeUriPath +" " + dateFormatter().format(new Date(System.currentTimeMillis())));
		}

		return new GenericClientHTTPResponseImpl(getHealthCheck()).obtainHttpResponse(
				new GenericClientBuilderImpl(getHealthCheck()).createWebResource(completeUriPath, params, clientProperties,headersNamedValuePair,cookies),
				clientProperties.get("serviceName"),
				clientProperties.get("httpMethod"),
				(short) Integer.parseInt(clientProperties.get("minThreads")),
				(short) Integer.parseInt(clientProperties.get("maxThreads")),
				Long.parseLong(clientProperties.get("keepAliveTimeout")),
				(short) Integer.parseInt(clientProperties.get("queueSize")));
	}  
	

	/****
	 *  This method is used for consumers with the expectation to obtain the whole http response
	 *  @params completeUriPath
	 *  @params  params
	 *  @client clientProperties 
	 *  @return ODServiceRESTClientResponse
	 *  @throws GenericClientException
	 *  
	 */
	public ODServiceRESTClientResponse invokeRestTemplateReturnsEntityResponse(final String completeUriPath, final Map<String, String> params, final Map<String, String> clientProperties) 
			throws GenericClientException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + "invokeRestTemplateReturnsEntityResponse - GenericTemplate  with  " +  completeUriPath +" " + dateFormatter().format(new Date(System.currentTimeMillis())));
		}

		return new GenericClientHTTPResponseImpl(getHealthCheck()).obtainHttpResponse(
				new GenericClientBuilderImpl(getHealthCheck()).createWebResource(completeUriPath, params, clientProperties,new HashMap<String, List<Object>>(),new HashMap<String,String>()),
				clientProperties.get("serviceName"),
				clientProperties.get("httpMethod"),
				(short) Integer.parseInt(clientProperties.get("minThreads")),
				(short) Integer.parseInt(clientProperties.get("maxThreads")),
				Long.parseLong(clientProperties.get("keepAliveTimeout")),
				(short) Integer.parseInt(clientProperties.get("queueSize")));
	}




}