package com.officedepot.service.generic.client.impl;

import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.ODGenericClientConfig;
import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.officedepot.services.core.httpclient.impl.NoWaitThreadSafeClientConnManager;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public enum GenericRestClientConfig implements ODGenericClientConfig {

	CONFIG_INSTANCE;
	private final static Logger log = Logger
			.getLogger(GenericRestClientConfig.class);

	
	private GenericRestClientConfig() {
	}

	public DefaultClientConfig createGenericClientConfig(final HttpClientParameters parameters) {
		final DefaultClientConfig clientConfig = new DefaultClientConfig();
		ThreadSafeClientConnManager ccm = new NoWaitThreadSafeClientConnManager();
		NoWaitThreadSafeClientConnManager.setMaxConnections(ccm, parameters);
		clientConfig.getProperties().put("PROPERTY_CONNECTION_MANAGER", ccm);
		log.debug("Client Config is activated");
		return clientConfig;

	}

}