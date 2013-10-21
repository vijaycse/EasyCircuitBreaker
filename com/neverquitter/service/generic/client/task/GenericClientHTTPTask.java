package com.neverquitter.service.generic.client.task;

import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpStatus;

import com.officedepot.service.generic.client.impl.ODServiceRESTClientResponse;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientUtility;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public class GenericClientHTTPTask<T>  implements Callable<T>{

	private Builder requestResource;
	private String httpMethod;
	private  Class<T> expectedPojo;
	private boolean returnHttpResponse;


	public GenericClientHTTPTask(Builder requestResource,String httpMethod, Class<T> expectedPojo,boolean returnHttpResponse){
		this.requestResource=requestResource;
		this.httpMethod=httpMethod;
		this.expectedPojo=expectedPojo;
		this.returnHttpResponse=returnHttpResponse;
	}

	/**
	 * creating a task with callable 
	 */
	@SuppressWarnings("unchecked")
	public T call() throws Exception {
		T t = null;
		ClientResponse response;
		// setting POST  otherwise get
		response =  httpMethod.equalsIgnoreCase("post") ? requestResource.post(ClientResponse.class) : requestResource.get(ClientResponse.class); 
		if(!returnHttpResponse){ // determining whole http response return  or returning an object
			String entityHeader = fetchEntityHeaderFromResponse(response);
			// marshalling the json otherwise text
			t= (T) (entityHeader.contains("json") ? GenericClientUtility.convertJSONtoPOJO((response.getEntity(String.class)),expectedPojo) : GenericClientUtility.convertTexttoGeneric((response.getEntity(String.class))));
			return t;
		}
		else{
			return (T) new ODServiceRESTClientResponse(response);
		}

	}


	/**
	 * 
	 * @param response
	 * @return
	 * @throws GenericClientException
	 */
	private String fetchEntityHeaderFromResponse(final ClientResponse response)
			throws GenericClientException {
		int httpStatus = response.getStatus();
		if (httpStatus == HttpStatus.SC_OK) {
			final MultivaluedMap<String, String> mapHeader = response.getHeaders();
			final List<String> entityHeader = mapHeader.get("Content-Type");
			return (null == entityHeader ? "" : entityHeader.get(0));
		} else {
			throw new GenericClientException("HTTP_STATUS", String.valueOf(httpStatus));
		}
	}



	public Builder getRequestResource() {
		return requestResource;
	}

	public void setRequestResource(Builder requestResource) {
		this.requestResource = requestResource;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Class<T> getExpectedPojo() {
		return expectedPojo;
	}

	public void setExpectedPojo(Class<T> expectedPojo) {
		this.expectedPojo = expectedPojo;
	}

	public boolean isReturnHttpResponse() {
		return returnHttpResponse;
	}

	public void setReturnHttpResponse(boolean returnHttpResponse) {
		this.returnHttpResponse = returnHttpResponse;
	}


}
