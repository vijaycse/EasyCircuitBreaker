package com.officedepot.service.generic.client.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.ClientResponse;

public class ODServiceRESTClientResponse {


	private ClientResponse resoponse;

	public ODServiceRESTClientResponse(ClientResponse response){
		this.resoponse=response;
	}

	/***
	 *  This returns all headers
	 * @return
	 */
	public List<String> getResponseAllHeaders(){
		List<String> entityHeader = new ArrayList<String>();
		final MultivaluedMap<String, String> mapHeader = resoponse.getHeaders();
		entityHeader.addAll(mapHeader.get("Content-Type"));
		entityHeader.addAll(mapHeader.get("WWW-Authenticate"));
		entityHeader.addAll(mapHeader.get("Status"));
		entityHeader.addAll(mapHeader.get("Last-Modified"));
		entityHeader.addAll(mapHeader.get("Expires"));
		entityHeader.addAll(mapHeader.get("Warning"));
		entityHeader.addAll(mapHeader.get("Cache-Control"));
		entityHeader.addAll(mapHeader.get("ETag"));
		return entityHeader;
	}


	/***
	 * This returns specific headers
	 * @param headerType
	 * @return
	 */
	public String getResponseHeader(String headerType){
		final MultivaluedMap<String, String> mapHeader = resoponse.getHeaders();
		return mapHeader.get(headerType).get(0);
	}


	/**
	 * This returns entire message
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public  <T> T getResponseEntity(Class<T> clazz){
		return  resoponse.getEntity(clazz); 
	}


	/**
	 * This returns status
	 * @return
	 */
	public int getResponseHttpStatus(){
		return resoponse.getStatus();
	}

}
