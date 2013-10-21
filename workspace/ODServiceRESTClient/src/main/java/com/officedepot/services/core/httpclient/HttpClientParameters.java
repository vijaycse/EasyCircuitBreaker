package com.officedepot.services.core.httpclient;

public interface HttpClientParameters {

	int getSocketTimeoutMillis();

	int getConnectionTimeoutMillis();

	int getMaxConnections();
}
