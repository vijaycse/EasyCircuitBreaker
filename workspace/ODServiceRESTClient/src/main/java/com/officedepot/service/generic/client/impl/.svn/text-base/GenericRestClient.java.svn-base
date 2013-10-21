package com.officedepot.service.generic.client.impl;

import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.ODGenericClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;

public enum GenericRestClient implements ODGenericClient {

	CLIENT_INSTANCE;
	private final static Logger LOGGER = Logger.getLogger(GenericRestClientConfig.class);
	// for asynch access only
	private final static short MAX_POOL_SIZE = 20;

	private GenericRestClient() {
	}

	public Client setRestClient(final DefaultClientConfig clientConfig) {
		final Client restClient = Client.create(clientConfig);
		restClient.getProperties().put(
				DefaultApacheHttpClient4Config.PROPERTY_THREADPOOL_SIZE,
				MAX_POOL_SIZE);
		LOGGER.debug("Client is actived");
		return restClient;
	}

}
