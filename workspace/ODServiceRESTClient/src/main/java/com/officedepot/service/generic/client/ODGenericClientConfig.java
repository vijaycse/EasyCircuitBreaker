package com.officedepot.service.generic.client;

import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public interface ODGenericClientConfig {

	 DefaultClientConfig createGenericClientConfig(HttpClientParameters parameters);
}
