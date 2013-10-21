package com.officedepot.service.generic.client.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.model.GenericClientInfo;

public class GenericClientConfiguratorUtility {


	private GenericClientInfo clientProperties = new GenericClientInfo();
	private final static Logger LOGGER = Logger.getLogger(GenericClientConfiguratorUtility.class);

	/***
	 * 
	 * @param httpMethod
	 * @param returnRepresentation
	 * @param serviceName
	 * return this
	 */
	public GenericClientConfiguratorUtility(String httpMethod,String contentType,String serviceName) {
		clientProperties.setHttpMethod(httpMethod);
		clientProperties.setContentType(contentType);
		clientProperties.setServiceName(serviceName);
	}

	/**
	 * 
	 * @param maxConnection
	 * @param connectionTimeout
	 * @param readTimeout
	 * @return
	 */
	public GenericClientConfiguratorUtility buildConnectionPool(String maxConnection, String connectionTimeout) {
		clientProperties.setMaxConnection(maxConnection);
		clientProperties.setConnectionTimeout(connectionTimeout);
		return this;
	}

	/**
	 * 
	 * @param failureCountAllowed
	 * @param serviceBreakerTimer
	 * @return
	 */
	public GenericClientConfiguratorUtility buildCircuitBreaker(String failureCountAllowed, String serviceBreakerTimer) {
		clientProperties.setFailureCountAllowed(failureCountAllowed);
		clientProperties.setServiceBreakerTimer(serviceBreakerTimer);
		return this;
	}
	/**
	 * 
	 * @param minThreads
	 * @param maxThreads
	 * @return
	 */
	public GenericClientConfiguratorUtility buildThreadPool(String minThreads, String maxThreads,String keepAliveTime,String queueSize){
		clientProperties.setMinThreads(minThreads);
		clientProperties.setMaxThreads(maxThreads);
		clientProperties.setKeepAliveTimeout(keepAliveTime);
		clientProperties.setQueueSize(queueSize);
		return this;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */	
	public GenericClientConfiguratorUtility buildBasicAuthentication(String username, String password) {
		clientProperties.setBasicAuthenticationUsername(username);
		clientProperties.setBasicAuthenticationPassword(password);
		return this;
	}	

	private static final String DEFAULT_MAX_CONNECTIONS="20";
	private static final String DEFAULT_CONNECTION_TIMEOUT="8000";
	private static final String DEFAULT_MIN_THREADS="5";
	private static final String DEFAULT_MAX_THREADS="10";
	private static final String DEFAULT_KEEP_ALIVE_TIMEOUT="1000";
	private static final String DEFAULT_QUEUESIZE="100";
	private static final String DEFAULT_FAILURE_COUNT="5";
	private static final String DEFAULT_BREAKER_TIMER="60000";
	private static final String DEFAULT_BASIC_AUTH_USERNAME=null;
	private static final String DEFAULT_BASIC_AUTH_PASSWORD=null;	

	/**creating a Map with the values
	 * 
	 * @return
	 * @throws GenericClientException 
	 */
	public Map<String, String> createProperties() throws GenericClientException{
		Map<String,String> clientConfigurationProperties = new HashMap<String,String>();

		String httpMethod = clientProperties.getHttpMethod();
		if(null==httpMethod || httpMethod.length() ==0 || !HttpMethod.checkHttpMethod(httpMethod))
			throw new GenericClientException("1002", "Invalid httpMethod");
		else clientConfigurationProperties.put("httpMethod", httpMethod);

		String contentType=clientProperties.getContentType();
		if(null==contentType || clientProperties.getContentType().length() ==0 || !Representation.checkRepresentation(contentType))
			throw new GenericClientException("1002", "Invalid representation");
		else clientConfigurationProperties.put("contentType", contentType);

		final String bau = clientProperties.getBasicAuthenticationUsername();
		clientConfigurationProperties.put(
				"basicAuthenticationUsername",
				null == bau || 0 == bau.trim().length() ? DEFAULT_BASIC_AUTH_USERNAME : bau
				);

		final String bap = clientProperties.getBasicAuthenticationPassword();
		clientConfigurationProperties.put(
				"basicAuthenticationPassword",
				null == bap || 0 == bap.trim().length() ? DEFAULT_BASIC_AUTH_PASSWORD : bap
				);		

		String maxConnection=clientProperties.getMaxConnection();
		if(null==maxConnection || maxConnection.length() ==0 || !StringUtils.isNumeric(maxConnection) )
			clientConfigurationProperties.put("maxConnection", DEFAULT_MAX_CONNECTIONS);
		else clientConfigurationProperties.put("maxConnection", maxConnection);

		String serviceName = clientProperties.getServiceName();
		if(null==serviceName || serviceName.length() ==0)
			throw new GenericClientException("1002", "Invalid Service Name");
		else clientConfigurationProperties.put("serviceName", serviceName);

		String connectionTimeout = clientProperties.getConnectionTimeout();
		if(null==connectionTimeout ||connectionTimeout.length() ==0 || !StringUtils.isNumeric(connectionTimeout))
			clientConfigurationProperties.put("connectionTimeout", DEFAULT_CONNECTION_TIMEOUT);
		else clientConfigurationProperties.put("connectionTimeout", connectionTimeout);

		String minThreads=clientProperties.getMinThreads();
		if(null==minThreads|| minThreads.length() ==0 || !StringUtils.isNumeric(minThreads))
			clientConfigurationProperties.put("minThreads", DEFAULT_MIN_THREADS);
		else clientConfigurationProperties.put("minThreads", minThreads);

		String maxThreads = clientProperties.getMaxThreads();
		if(null==maxThreads || maxThreads.length() ==0 || !StringUtils.isNumeric(maxThreads))
			clientConfigurationProperties.put("maxThreads", DEFAULT_MAX_THREADS);
		else clientConfigurationProperties.put("maxThreads", maxThreads);

		String keepAliveTimeout = clientProperties.getKeepAliveTimeout();
		if(null==keepAliveTimeout || keepAliveTimeout.length() ==0 || !StringUtils.isNumeric(keepAliveTimeout))
			clientConfigurationProperties.put("keepAliveTimeout", DEFAULT_KEEP_ALIVE_TIMEOUT);
		else clientConfigurationProperties.put("keepAliveTimeout", keepAliveTimeout);


		String queueSize = clientProperties.getQueueSize();
		if(null==queueSize || queueSize.length() ==0 || !StringUtils.isNumeric(queueSize))
			clientConfigurationProperties.put("queueSize", DEFAULT_QUEUESIZE);
		else clientConfigurationProperties.put("queueSize", queueSize);


		String failureCountAllowed = clientProperties.getFailureCountAllowed();
		if(null==failureCountAllowed || failureCountAllowed.length() ==0 || !StringUtils.isNumeric(failureCountAllowed))
			clientConfigurationProperties.put("failureCountAllowed",  DEFAULT_FAILURE_COUNT);
		else clientConfigurationProperties.put("failureCountAllowed", failureCountAllowed);

		String serviceBreakerTimer = clientProperties.getServiceBreakerTimer();
		if(null==serviceBreakerTimer || serviceBreakerTimer.length() ==0 || !StringUtils.isNumeric(serviceBreakerTimer))
			clientConfigurationProperties.put("serviceBreakerTimer", DEFAULT_BREAKER_TIMER);
		else clientConfigurationProperties.put("serviceBreakerTimer", serviceBreakerTimer);

		LOGGER.debug(" configuration  result " + clientConfigurationProperties.toString());
		return clientConfigurationProperties;
	}




	@Override
	public String toString() {
		return "GenericClientConfiguratorUtility [clientProperties="
				+ clientProperties.toString() + "]";
	}




	public enum Representation {
		TEXT, JSON,FORMURLENCODED;

		public static boolean checkRepresentation(String representation) {
			if ((Representation.JSON.toString()	.equalsIgnoreCase(representation)) || (Representation.TEXT.toString().equalsIgnoreCase(representation)) 
					|| (Representation.FORMURLENCODED.toString().equalsIgnoreCase(representation)) )
				return true;
			else
				return false;

		}

	}

	public enum HttpMethod {
		GET, POST;

		public static boolean checkHttpMethod(String httpMethod) {
			if ((HttpMethod.GET.toString().equalsIgnoreCase(httpMethod)) || (HttpMethod.POST.toString().equalsIgnoreCase(httpMethod)))
				return true;
			else
				return false;

		}

	}
}
