package com.officedepot.service.generic.client.impl;


import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientUtility;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public enum GenericRestWebResource {

	WEBRESOURCE_INSTANCE;

	private static final Logger LOGGER = Logger.getLogger(GenericRestWebResource.class);

	private GenericRestWebResource() {
	}

	private static final String CONTENT_TYPE = "Content-Type";

	/**
	 * 1) converting util.map to multivaluedmap 
	 * 2) setting payload for post and queryparam for get 
	 * 3) setting header 
	 * 4) building resource builder
	 * 
	 * @param client
	 * @param completeUriPath
	 * @param params
	 * @param contentType
	 * @param httpMethod
	 * @param socketTimeout2
	 * @param connectionTimeout2
	 * @return
	 * @throws GenericClientException
	 */
	public com.sun.jersey.api.client.WebResource.Builder buildRequest(final Client client, final String completeUriPath,final Map<String, String> params, final int connectionTimeout,
			final String httpMethod,final String contentType, final String basicAuthUsername, final String basicAuthPassword,final Map<String,List<Object>> headersNamedValuePair,final Map<String,String> cookies)
					throws GenericClientException {
		if (httpMethod.equalsIgnoreCase("POST")) {
			LOGGER.debug(" building resource for POST with " + completeUriPath);
			return buildWebResourceForPost(client, completeUriPath,params, connectionTimeout, contentType, basicAuthUsername, basicAuthPassword,headersNamedValuePair,cookies);
		} else {
			LOGGER.debug(" building resource for GET with " + completeUriPath);
			return buildWebResourceForGet(client, completeUriPath,GenericClientUtility.convertMaptoMultiValuedMap(params),connectionTimeout, contentType, basicAuthUsername, basicAuthPassword,headersNamedValuePair,cookies);
		}
	}

	/**
	 * 
	 * @param client
	 * @param completeUriPath
	 * @param params
	 * @param socketTimeout
	 * @param connectionTimeout
	 * @param contentType
	 * @return
	 */
	private com.sun.jersey.api.client.WebResource.Builder buildWebResourceForGet(Client client, String completeUriPath,	MultivaluedMap<String, String> params, int connectionTimeout,
			String contentType, String basicAuthUsername, String basicAuthPassword,Map<String,List<Object>> headersNamedValuePair,Map<String,String> cookies) {
		WebResource webResource = client.resource(UriBuilder.fromUri(completeUriPath).build()).queryParams(params);
		webResource = setResourceProperties(webResource, connectionTimeout);
		webResource = setClientFilters(webResource, basicAuthUsername, basicAuthPassword,headersNamedValuePair);
		return createResourceBuilder(webResource, contentType,cookies);
	}

	/**
	 * 
	 * @param client
	 * @param completeUriPath
	 * @param params
	 * @param socketTimeout
	 * @param connectionTimeout
	 * @param contentType
	 * @return
	 */
	private com.sun.jersey.api.client.WebResource.Builder buildWebResourceForPost(Client client, String completeUriPath, Map<String, String> params,int connectionTimeout,
			String contentType, String basicAuthUsername, String basicAuthPassword,Map<String,List<Object>> headersNamedValuePair,Map<String,String> cookies) {
		WebResource webResource = client.resource(UriBuilder.fromUri(completeUriPath).build());
		String requestEntity = getPayloadFromParam(params);
		webResource = setResourceProperties(webResource, connectionTimeout);
		webResource = setClientFilters(webResource, basicAuthUsername, basicAuthPassword,headersNamedValuePair);		
		return createResourceBuilder(webResource, contentType,cookies).entity(requestEntity);
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	private String getPayloadFromParam(Map<String, String> params) {
		String payLoad = "";
		StringBuilder queryParams = new StringBuilder();
		if (null != params && params.size() > 0) {
			payLoad = params.get("payload"); // simple payload type post
			if(null==payLoad||payLoad.length()<=0){ // named value pair type post 
				for (Map.Entry<String, String> entry : params.entrySet()) {
					queryParams.append("&"+entry.getKey()+"="+entry.getValue());
				}
				payLoad=queryParams.toString();
			}
		}
		LOGGER.debug(" payload " + payLoad);
		return payLoad;
	}

	/**
	 * 
	 * @param resource
	 * @param contentType
	 * @return
	 */
	private com.sun.jersey.api.client.WebResource.Builder createResourceBuilder(WebResource resource, String contentType ,Map<String,String> requestCookies) {
		WebResource.Builder builder;
		if (contentType.equalsIgnoreCase("TEXT")) {
			String acceptHeader = MediaType.TEXT_PLAIN;
			LOGGER.debug(" setting header with TEXT");
			builder = resource.accept(MediaType.valueOf(acceptHeader)).header(CONTENT_TYPE, acceptHeader);
		} else if(contentType.equalsIgnoreCase("FORMURLENCODED")){
			String acceptHeader = MediaType.APPLICATION_FORM_URLENCODED;
			LOGGER.debug(" setting header with application url encoded");
			builder = resource.accept(MediaType.valueOf(acceptHeader)).header(CONTENT_TYPE, acceptHeader);
		}
		else {
			String acceptHeader = MediaType.APPLICATION_JSON;
			LOGGER.debug(" setting header with JSON");
			builder =  resource.accept(MediaType.valueOf(acceptHeader)).header(CONTENT_TYPE, acceptHeader);
		}
		if(null!=requestCookies && !requestCookies.isEmpty()){
			for (Map.Entry<String,String> entry : requestCookies.entrySet()) {
				builder.cookie(GenericClientUtility.convertEntrySetToCookie(entry));
			}
		}
		return builder;
	}

	/**
	 * 
	 * @param webResource
	 * @param socketTimeout
	 * @param connectionTimeout
	 * @return
	 */
	private WebResource setResourceProperties(WebResource webResource, int connectionTimeout) {
		webResource.setProperty(ClientConfig.PROPERTY_CONNECT_TIMEOUT,connectionTimeout);
		webResource.setProperty(ClientConfig.PROPERTY_READ_TIMEOUT,connectionTimeout);
		return webResource;
	}

	/**
	 * 
	 * @param webResource
	 * @param basicAuthUsername
	 * @param basicAuthPassword
	 * @return
	 */	
	public WebResource setClientFilters(final WebResource webResource, final String basicAuthUsername, final String basicAuthPassword,final Map<String,List<Object>> headersNamedValuePair) {
		if (! StringUtils.isEmpty(basicAuthUsername) && ! StringUtils.isEmpty(basicAuthPassword)) {
			LOGGER.debug("Setting up basic Auth");
			webResource.addFilter(new HTTPBasicAuthFilter(basicAuthUsername, basicAuthPassword));	
		}
		if(null!=headersNamedValuePair && !headersNamedValuePair.isEmpty()){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Setting up HEADERS");
				for (Map.Entry<String,List<Object>> entry : headersNamedValuePair.entrySet()) {
					LOGGER.debug("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
				}
			}
			webResource.addFilter(new AddHeaderClientFilter(headersNamedValuePair));
		}
		return webResource;
	}
}
